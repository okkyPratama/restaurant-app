package com.dicoding.restaurantapp.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dicoding.restaurantapp.core.domain.usecase.RestaurantUseCase

class FavoriteViewModel(restaurantUseCase: RestaurantUseCase): ViewModel() {
    val favoriteRestaurant = restaurantUseCase.getFavoriteRestaurant().asLiveData()
}