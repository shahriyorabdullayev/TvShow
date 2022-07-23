package com.shahriyor.android_imperative.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.core.app.ActivityOptionsCompat
import androidx.core.view.ViewCompat
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shahriyor.android_imperative.R
import com.shahriyor.android_imperative.ui.adapter.TVShowAdapter
import com.shahriyor.android_imperative.databinding.ActivityMainBinding
import com.shahriyor.android_imperative.model.TVShow
import com.shahriyor.android_imperative.utils.Logger
import com.shahriyor.android_imperative.ui.viewmodel.MainViewModel

class MainActivity : BaseActivity() {

//    private val TAG = MainActivity::class.java.simpleName
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
//    val viewModel: MainViewModel by viewModels()
//    lateinit var adapter: TVShowAdapter

    private lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        navController = Navigation.findNavController(this, R.id.main_container)

        binding.bottomNavigation.setupWithNavController(findNavController(R.id.main_container))

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.detailsFragment -> hideBottomNav()
                else -> showBottomNav()
            }
        }
    }

    private fun hideBottomNav() {
        binding.bottomNavigation.visibility = View.GONE
    }

    private fun showBottomNav() {
        binding.bottomNavigation.visibility = View.VISIBLE
    }


}