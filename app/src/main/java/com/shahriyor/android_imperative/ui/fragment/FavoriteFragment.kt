package com.shahriyor.android_imperative.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.shahriyor.android_imperative.R
import com.shahriyor.android_imperative.databinding.FragmentFavoriteBinding
import com.shahriyor.android_imperative.ui.adapter.TVShowAdapter
import com.shahriyor.android_imperative.ui.viewmodel.MainViewModel
import com.shahriyor.android_imperative.utils.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragment : Fragment(R.layout.fragment_favorite) {

    private val binding by viewBinding { FragmentFavoriteBinding.bind(it) }
    private val viewModel: MainViewModel by viewModels()
    private val adapter by lazy { TVShowAdapter() }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getTvShows()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvHome.adapter = adapter
        val lm = GridLayoutManager(requireContext(), 2)
        binding.rvHome.layoutManager = lm
        initObserver()

    }

    private fun initObserver() {
        viewModel.tvShowsFromDB.observe(viewLifecycleOwner){
            adapter.submitList(it)
        }


    }

}