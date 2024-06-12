package com.dicoding.restaurantapp.core.data.source.local.room

import androidx.room.*
import com.dicoding.restaurantapp.core.data.source.local.entity.RestaurantEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RestaurantDao {

    @Query("SELECT * FROM restaurant")
    fun getAllRestaurant(): Flow<List<RestaurantEntity>>

    @Query("SELECT * FROM restaurant where isFavorite = 1")
    fun getFavoriteRestaurant(): Flow<List<RestaurantEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRestaurant(restaurant: List<RestaurantEntity>)

    @Update
    fun updateFavoriteRestaurant(restaurant: RestaurantEntity)
}