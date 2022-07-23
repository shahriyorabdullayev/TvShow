package com.shahriyor.android_imperative.ui.fragment

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavOptions
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shahriyor.android_imperative.R
import com.shahriyor.android_imperative.databinding.FragmentHomeBinding
import com.shahriyor.android_imperative.model.TVShow
import com.shahriyor.android_imperative.ui.adapter.TVShowAdapter
import com.shahriyor.android_imperative.ui.viewmodel.MainViewModel
import com.shahriyor.android_imperative.utils.Logger
import com.shahriyor.android_imperative.utils.viewBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {
    private val TAG = HomeFragment::class.java.simpleName

    private val binding by viewBinding { FragmentHomeBinding.bind(it) }
    private val viewModel: MainViewModel by viewModels()
    private val adapter by lazy { TVShowAdapter() }

    private val list = ArrayList<TVShow>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.apiTVShowPopular(1)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvHome.adapter = adapter

        initViews()
        observeViewModel()

    }

    private fun initViews() {

        val lm = GridLayoutManager(requireContext(), 2)
        binding.rvHome.layoutManager = lm

        adapter.onItemClick = { tvShow, imageView ->
            viewModel.insertTVShowsDb(tvShow)
            callDetailsFragment(tvShow, imageView)
        }


        binding.rvHome.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (lm.findLastCompletelyVisibleItemPosition() == (adapter.itemCount - 1)) {
                    val nextPage = viewModel.tvShowPopular.value!!.page + 1
                    val totalPage = viewModel.tvShowPopular.value!!.pages
                    if (nextPage <= totalPage) {
                        viewModel.apiTVShowPopular(nextPage)
                    }
                }
            }
        })

        binding.bFab.setOnClickListener {
            binding.rvHome.smoothScrollToPosition(0)
        }


    }

    private fun observeViewModel() {
        viewModel.tvShowsFromApi.observe(viewLifecycleOwner) {
            Logger.d(TAG, it!!.size.toString())
            list.addAll(it)
            adapter.submitList(list.toList())
        }

        viewModel.errorMessage.observe(viewLifecycleOwner) {
            Logger.d(TAG, it.toString())
        }

        viewModel.isLoading.observe(viewLifecycleOwner) {
            Logger.d(TAG, it!!.toString())
            if (it) {
                binding.pbLoading.visibility = View.VISIBLE
            } else {
                binding.pbLoading.visibility = View.GONE
            }
        }
    }

    private fun callDetailsFragment(tvShow: TVShow, ivMovie: ImageView) {
        val bundle = Bundle().apply {
            putLong("show_id", tvShow.id!!)
            putString("show_img", tvShow.image_thumbnail_path)
            putString("show_name", tvShow.name)
            putString("show_network", tvShow.network)
            //putString("iv_movie", ViewCompat.getTransitionName(ivMovie))
        }
        val extras = FragmentNavigatorExtras(ivMovie to "iv_detail")

        findNavController().navigate(R.id.action_homeFragment_to_detailsFragment,
            bundle,
            null,
            extras)
    }


}