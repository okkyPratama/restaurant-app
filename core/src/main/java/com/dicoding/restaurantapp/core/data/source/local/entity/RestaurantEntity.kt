package com.dicoding.restaurantapp.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "restaurant")
data class RestaurantEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "restaurantId")
    val restaurantId: String,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "description")
    val description: String,

    @ColumnInfo(name = "pictureId")
    val pictureId: String,

    @ColumnInfo(name = "city")
    val city: String,

    @ColumnInfo(name = "rating")
    val rating: Double,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false

)
