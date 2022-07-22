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



//        initViews()
    }
//
//    private fun initViews() {
//        initObservers()
//        val lm = GridLayoutManager(this, 2)
//        binding.rvHome.layoutManager = lm
//        refreshAdapter(ArrayList())
//
//        binding.rvHome.addOnScrollListener(object : RecyclerView.OnScrollListener() {
//            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//                super.onScrolled(recyclerView, dx, dy)
//                if (lm.findLastCompletelyVisibleItemPosition() == (adapter.itemCount - 1)) {
//                    val nextPage = viewModel.tvShowPopular.value!!.page + 1
//                    val totalPage = viewModel.tvShowPopular.value!!.pages
//                    if (nextPage <= totalPage) {
//                        viewModel.apiTVShowPopular(nextPage)
//                    }
//                }
//            }
//        })
//
//        binding.bFab.setOnClickListener {
//            binding.rvHome.smoothScrollToPosition(0)
//        }
//        viewModel.apiTVShowPopular(1)
//    }
//
//    private fun initObservers() {
//        /**
//         * Retrofit Related
//         */
//        viewModel.tvShowsFromApi.observe(this) {
//            Logger.d(TAG, it!!.size.toString())
//            adapter.setNewTVShows(it)
//        }
//
//        viewModel.errorMessage.observe(this) {
//            Logger.d(TAG, it.toString())
//        }
//
//        viewModel.isLoading.observe(this) {
//            Logger.d(TAG, it!!.toString())
//            if (it) {
//                binding.pbLoading.visibility = View.VISIBLE
//            } else {
//                binding.pbLoading.visibility = View.GONE
//            }
//        }
//    }
//
//    private fun refreshAdapter(items: ArrayList<TVShow>) {
//        adapter = TVShowAdapter(this, items)
//        binding.rvHome.adapter = adapter
//    }
//
//    fun callDetailsActivity(tvShow: TVShow, ivMovie: ImageView) {
//        val intent = Intent(this, DetailsActivity::class.java).apply {
//            putExtra("show_id", tvShow.id)
//            putExtra("show_img", tvShow.image_thumbnail_path)
//            putExtra("show_name", tvShow.name)
//            putExtra("show_network", tvShow.network)
//            putExtra("iv_movie", ViewCompat.getTransitionName(ivMovie))
//        }
//
//        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this,
//            ivMovie,
//            ViewCompat.getTransitionName(ivMovie)!!)
//
//        startActivity(intent, options.toBundle())
//    }


}