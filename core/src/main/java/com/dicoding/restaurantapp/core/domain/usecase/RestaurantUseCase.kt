package com.dicoding.restaurantapp.core.domain.usecase

import com.dicoding.restaurantapp.core.data.Resource
import com.dicoding.restaurantapp.core.domain.model.Restaurant
import kotlinx.coroutines.flow.Flow

interface RestaurantUseCase {

    fun getAllRestaurant(): Flow<Resource<List<Restaurant>>>
    fun getFavoriteRestaurant(): Flow<List<Restaurant>>
    fun setFavoriteRestaurant(restaurant: Restaurant, state: Boolean)

}