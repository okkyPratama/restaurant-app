package com.dicoding.restaurantapp.core.data.source.local

import com.dicoding.restaurantapp.core.data.source.local.room.RestaurantDao
import com.dicoding.restaurantapp.core.data.source.local.entity.RestaurantEntity

class LocalDataSource(private val restaurantDao: RestaurantDao) {

        fun getAllRestaurant() = restaurantDao.getAllRestaurant()

        fun getFavoriteRestaurant() = restaurantDao.getFavoriteRestaurant()

        suspend fun insertRestaurant(restaurantList: List<RestaurantEntity>) = restaurantDao.insertRestaurant(restaurantList)

        fun setFavoriteRestaurant(restaurant: RestaurantEntity, newState: Boolean) {
            restaurant.isFavorite = newState
            restaurantDao.updateFavoriteRestaurant(restaurant)
        }
}