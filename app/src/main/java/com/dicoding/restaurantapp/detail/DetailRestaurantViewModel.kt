package com.dicoding.restaurantapp.detail

import androidx.lifecycle.ViewModel
import com.dicoding.restaurantapp.core.domain.model.Restaurant
import com.dicoding.restaurantapp.core.domain.usecase.RestaurantUseCase

class DetailRestaurantViewModel(private val restaurantUseCase: RestaurantUseCase) : ViewModel() {
    fun setFavoriteRestaurant(restaurant: Restaurant, newStatus:Boolean) =
        restaurantUseCase.setFavoriteRestaurant(restaurant, newStatus)
}