package com.dicoding.restaurantapp.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dicoding.restaurantapp.core.domain.usecase.RestaurantUseCase

class HomeViewModel(restaurantUseCase: RestaurantUseCase) : ViewModel() {
    val restaurant = restaurantUseCase.getAllRestaurant().asLiveData()
}