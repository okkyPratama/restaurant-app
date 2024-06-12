package com.dicoding.restaurantapp.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ListRestaurantsResponse(
    @field:SerializedName("error")
    val error: Boolean,

    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("restaurants")
    val restaurants: List<RestaurantResponse>

)

