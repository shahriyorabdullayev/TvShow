package com.shahriyor.android_imperative.ui.fragment

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.shahriyor.android_imperative.R
import com.shahriyor.android_imperative.databinding.FragmentDetailsBinding
import com.shahriyor.android_imperative.ui.adapter.TVEpisodeAdapter
import com.shahriyor.android_imperative.ui.adapter.TVShortAdapter
import com.shahriyor.android_imperative.ui.viewmodel.DetailsViewModel
import com.shahriyor.android_imperative.utils.viewBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DetailsFragment : Fragment(R.layout.fragment_details) {

    private val binding by viewBinding { FragmentDetailsBinding.bind(it) }
    private val viewModel: DetailsViewModel by viewModels()
    private val adapter by lazy { TVShortAdapter(ArrayList()) }
    private val episodeAdapter by lazy { TVEpisodeAdapter() }

    private var showId = 0
    companion object{
        var showImg = ""
    }
    private var showName = ""
    private var showNetwork = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            showId = it.getLong("show_id").toInt()
            showImg = it.getString("show_img")!!
            showName = it.getString("show_name")!!
            showNetwork = it.getString("show_network")!!
        }

        val transition =
            TransitionInflater.from(requireContext()).inflateTransition(android.R.transition.move)
        sharedElementReturnTransition = transition
        sharedElementEnterTransition = transition
        viewModel.apiTVShowDetails(showId)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        binding.rvShorts.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvShorts.adapter = adapter

        binding.rvEpisode.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvEpisode.adapter = episodeAdapter


        binding.ivClose.setOnClickListener {
            requireActivity().onBackPressed()
        }
        binding.tvName.text = showName
        binding.tvType.text = showNetwork
        Glide.with(binding.ivDetail).load(showImg).into(binding.ivDetail)

        initObserver()

    }

    private fun initObserver() {
        viewModel.tvShowDetails.observe(viewLifecycleOwner) {
            binding.tvDetails.text = it.tvShow.description
            adapter.setData(it.tvShow.pictures as ArrayList<String>)
            episodeAdapter.submitList(it.tvShow.episodes)
        }

        viewModel.isLoading.observe(viewLifecycleOwner) {
            if (it) {
                binding.pbLoading.visibility = View.VISIBLE
            } else {
                binding.pbLoading.visibility = View.GONE
            }
        }
    }






}