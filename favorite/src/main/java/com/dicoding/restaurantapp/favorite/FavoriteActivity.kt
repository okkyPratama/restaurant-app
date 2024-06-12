package com.dicoding.restaurantapp.favorite

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.restaurantapp.core.ui.RestaurantAdapter
import com.dicoding.restaurantapp.detail.DetailRestaurantActivity
import com.dicoding.restaurantapp.favorite.databinding.ActivityFavoriteBinding
import com.dicoding.restaurantapp.favorite.di.favoriteModule
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteActivity : AppCompatActivity() {

    private val favoriteViewModel: FavoriteViewModel by viewModel()
    private lateinit var binding: ActivityFavoriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadKoinModules(favoriteModule)

        val restaurantAdapter = RestaurantAdapter()
        restaurantAdapter.onItemClick = { selectedData ->
            val uri = Uri.parse("restaurantapp://detail")
            val intent = Intent(Intent.ACTION_VIEW, uri).apply {
                putExtra(DetailRestaurantActivity.EXTRA_DATA, selectedData)
            }
            startActivity(intent)
        }

        favoriteViewModel.favoriteRestaurant.observe(this) { dataRestaurant ->
            restaurantAdapter.setData(dataRestaurant)
            binding.viewEmpty.root.visibility =
                if (dataRestaurant.isNotEmpty()) View.GONE else View.VISIBLE
        }

        with(binding.rvRestaurant) {
            layoutManager = LinearLayoutManager(this@FavoriteActivity)
            setHasFixedSize(true)
            adapter = restaurantAdapter
        }
    }
}