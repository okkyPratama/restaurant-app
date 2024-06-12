package com.dicoding.restaurantapp.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Restaurant(
    val restaurantId: String,
    val name: String,
    val description: String,
    val pictureId: String,
    val city: String,
    val rating: Double,
    val isFavorite: Boolean
) : Parcelable
