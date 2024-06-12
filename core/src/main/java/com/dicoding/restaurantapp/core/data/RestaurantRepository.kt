package com.dicoding.restaurantapp.core.data

import com.dicoding.restaurantapp.core.data.source.local.LocalDataSource
import com.dicoding.restaurantapp.core.data.source.remote.RemoteDataSource
import com.dicoding.restaurantapp.core.data.source.remote.network.ApiResponse
import com.dicoding.restaurantapp.core.data.source.remote.response.RestaurantResponse
import com.dicoding.restaurantapp.core.domain.model.Restaurant
import com.dicoding.restaurantapp.core.domain.repository.IRestaurantRepository
import com.dicoding.restaurantapp.core.utils.AppExecutors
import com.dicoding.restaurantapp.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RestaurantRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IRestaurantRepository {

        override fun getAllRestaurant() : Flow<Resource<List<Restaurant>>> =
          object : NetworkBoundResource<List<Restaurant>, List<RestaurantResponse>>() {
              override fun loadFromDB(): Flow<List<Restaurant>> {
                  return localDataSource.getAllRestaurant().map {
                      DataMapper.mapEntitiesToDomain(it)
                  }
              }

              override fun shouldFetch(data: List<Restaurant>?): Boolean =
                  true

              override suspend fun createCall(): Flow<ApiResponse<List<RestaurantResponse>>> =
                  remoteDataSource.getAllRestaurant()

              override suspend fun saveCallResult(data: List<RestaurantResponse>) {
                  val restaurantList = DataMapper.mapResponsesToEntities(data)
                  localDataSource.insertRestaurant(restaurantList)
              }
          }.asFlow()


        override fun getFavoriteRestaurant(): Flow<List<Restaurant>> {
            return localDataSource.getFavoriteRestaurant().map {
                DataMapper.mapEntitiesToDomain(it)
            }
        }

        override fun setFavoriteRestaurant(restaurant: Restaurant, state: Boolean) {
            val restaurantEntity = DataMapper.mapDomainToEntity(restaurant)
            appExecutors.diskIO().execute { localDataSource.setFavoriteRestaurant(restaurantEntity, state) }
        }
}


