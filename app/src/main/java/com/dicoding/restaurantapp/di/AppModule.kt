package com.dicoding.restaurantapp.di

import com.dicoding.restaurantapp.core.domain.usecase.RestaurantInteractor
import com.dicoding.restaurantapp.core.domain.usecase.RestaurantUseCase
import com.dicoding.restaurantapp.detail.DetailRestaurantViewModel
import com.dicoding.restaurantapp.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<RestaurantUseCase> { RestaurantInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { DetailRestaurantViewModel(get())}

}