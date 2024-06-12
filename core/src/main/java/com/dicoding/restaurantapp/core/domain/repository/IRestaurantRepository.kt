package com.dicoding.restaurantapp.core.domain.repository

import com.dicoding.restaurantapp.core.data.Resource
import com.dicoding.restaurantapp.core.domain.model.Restaurant
import kotlinx.coroutines.flow.Flow

interface IRestaurantRepository {

    fun getAllRestaurant(): Flow<Resource<List<Restaurant>>>

    fun getFavoriteRestaurant(): Flow<List<Restaurant>>

    fun setFavoriteRestaurant(restaurant: Restaurant, state: Boolean)

}