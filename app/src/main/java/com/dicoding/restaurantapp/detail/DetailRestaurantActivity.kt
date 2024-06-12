package com.dicoding.restaurantapp.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.dicoding.restaurantapp.R
import com.dicoding.restaurantapp.core.domain.model.Restaurant
import com.dicoding.restaurantapp.databinding.ActivityDetailRestaurantBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailRestaurantActivity : AppCompatActivity() {

    private val detailRestaurantViewModel: DetailRestaurantViewModel by viewModel()
    private lateinit var  binding: ActivityDetailRestaurantBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailRestaurantBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val detailRestaurant = intent.getParcelableExtra<Restaurant>(EXTRA_DATA)
        showDetailRestaurant(detailRestaurant)
    }

     private fun showDetailRestaurant(detailRestaurant: Restaurant?) {
        detailRestaurant?.let {
            Glide.with(this)
                .load("https://restaurant-api.dicoding.dev/images/large/${detailRestaurant.pictureId}")
                .into(binding.picture)
            binding.name.text = detailRestaurant.name
            binding.city.text = detailRestaurant.city
            binding.rating.text = detailRestaurant.rating.toString()
            binding.description.text = detailRestaurant.description

            var statusFavorite = detailRestaurant.isFavorite
            setStatusFavorite(statusFavorite)
            binding.favoriteFAB.setOnClickListener {
                statusFavorite = !statusFavorite
                detailRestaurantViewModel.setFavoriteRestaurant(detailRestaurant, statusFavorite)
                setStatusFavorite(statusFavorite)

                if (statusFavorite) {
                    Toast.makeText(this, "Successfully added to favorites", Toast.LENGTH_SHORT).show()
                }
                else {
                    Toast.makeText(this, "Successfully removed from favorites", Toast.LENGTH_SHORT).show()
                }
            }


        }
     }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.favoriteFAB.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_favorite))
        } else {
            binding.favoriteFAB.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_favorite_border))
        }
    }


    companion object {
        const val EXTRA_DATA = "extra_data"
    }

}