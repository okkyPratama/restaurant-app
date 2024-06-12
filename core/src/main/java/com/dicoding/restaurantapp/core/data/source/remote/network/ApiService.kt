package com.dicoding.restaurantapp.core.data.source.remote.network

import com.dicoding.restaurantapp.core.data.source.remote.response.ListRestaurantsResponse
import retrofit2.http.GET

interface ApiService {

    @GET("list")
   suspend fun getList(): ListRestaurantsResponse
}