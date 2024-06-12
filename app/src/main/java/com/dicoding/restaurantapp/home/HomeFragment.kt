package com.dicoding.restaurantapp.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.restaurantapp.R
import com.dicoding.restaurantapp.core.data.Resource
import com.dicoding.restaurantapp.core.ui.RestaurantAdapter
import com.dicoding.restaurantapp.databinding.FragmentHomeBinding
import com.dicoding.restaurantapp.detail.DetailRestaurantActivity
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModel()

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val restaurantAdapter = RestaurantAdapter()
            restaurantAdapter.onItemClick = { selectedData ->
                val intent = Intent(activity, DetailRestaurantActivity::class.java)
                intent.putExtra(DetailRestaurantActivity.EXTRA_DATA, selectedData)
                startActivity(intent)
            }

            homeViewModel.restaurant.observe(viewLifecycleOwner) { restaurant ->
                if (restaurant != null) {
                    when (restaurant) {
                        is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                        is Resource.Success -> {
                            binding.progressBar.visibility = View.GONE
                            restaurantAdapter.setData(restaurant.data)
                        }
                        is Resource.Error -> {
                            binding.progressBar.visibility = View.GONE
                            binding.viewError.root.visibility = View.VISIBLE
                            binding.viewError.tvError.text =
                                restaurant.message ?: getString(R.string.something_wrong)
                        }
                    }
                }
            }

            with(binding.rvRestaurant) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = restaurantAdapter
            }


        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}