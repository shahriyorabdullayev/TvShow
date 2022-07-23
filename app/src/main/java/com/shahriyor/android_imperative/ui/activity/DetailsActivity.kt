package com.shahriyor.android_imperative.ui.activity

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.shahriyor.android_imperative.ui.adapter.TVShortAdapter
import com.shahriyor.android_imperative.databinding.ActivityDetailsBinding
import com.shahriyor.android_imperative.utils.Logger
import com.shahriyor.android_imperative.ui.viewmodel.DetailsViewModel

class DetailsActivity : BaseActivity() {

    private val TAG = MainActivity::class.java.simpleName
    private val binding by lazy { ActivityDetailsBinding.inflate(layoutInflater) }

    private val viewModel: DetailsViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initViews()
    }

    private fun initViews() {

        initObserver()

        binding.rvShorts.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val extras = intent.extras
        val showId = extras!!.getLong("show_id")
        val showImg = extras.getString("show_img")
        val showName = extras.getString("show_name")
        val showNetwork = extras.getString("show_network")

        binding.ivClose.setOnClickListener {
            ActivityCompat.finishAfterTransition(this)
        }

        val imageTransitionName = extras.getString("iv_movie")
        binding.ivDetail.transitionName = imageTransitionName

        binding.tvName.text = showName
        binding.tvType.text = showNetwork
        Glide.with(this).load(showImg).into(binding.ivDetail)

        viewModel.apiTVShowDetails(showId.toInt())
    }

    private fun initObserver() {
        viewModel.tvShowDetails.observe(this) {
            Logger.d(TAG, it.toString())
            binding.tvDetails.text = it.tvShow.description
            //refreshAdapter(it.tvShow.pictures)
        }

        viewModel.errorMessage.observe(this) {
            Logger.d(TAG, it.toString())
        }

        viewModel.isLoading.observe(this) {
            Logger.d(TAG, it.toString())
            if (it) {
                binding.pbLoading.visibility = View.VISIBLE
            } else {
                binding.pbLoading.visibility = View.GONE
            }
        }


    }

//    private fun refreshAdapter(items: List<String>) {
//        val adapter = TVShortAdapter(this, items)
//        binding.rvShorts.adapter = adapter
//    }
}