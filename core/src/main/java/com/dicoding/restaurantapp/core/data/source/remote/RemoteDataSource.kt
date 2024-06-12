package com.dicoding.restaurantapp.core.data.source.remote

import com.dicoding.restaurantapp.core.data.source.remote.network.ApiResponse
import com.dicoding.restaurantapp.core.data.source.remote.network.ApiService
import com.dicoding.restaurantapp.core.data.source.remote.response.RestaurantResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource (private val apiService: ApiService) {

   suspend fun getAllRestaurant(): Flow<ApiResponse<List<RestaurantResponse>>> {

        return flow {
            try {
                val response = apiService.getList()
                val dataArray = response.restaurants
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.restaurants))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)

    }
}