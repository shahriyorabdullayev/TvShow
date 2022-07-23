package com.shahriyor.android_imperative.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.shahriyor.android_imperative.R
import com.shahriyor.android_imperative.databinding.FragmentDetailsBinding
import com.shahriyor.android_imperative.databinding.ItemEpisodeBinding
import com.shahriyor.android_imperative.ui.activity.DetailsActivity
import com.shahriyor.android_imperative.databinding.ItemTvShortBinding
import com.shahriyor.android_imperative.model.Episode
import com.shahriyor.android_imperative.model.TVShow
import com.shahriyor.android_imperative.ui.fragment.DetailsFragment

class TVEpisodeAdapter: ListAdapter<Episode, TVEpisodeAdapter.EpisodeViewHolder>(Comparator()) {

    inner class EpisodeViewHolder(private val binding: ItemEpisodeBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Episode) {
            binding.apply {
                tvEpisodeName.text = item.name
                tvEpisodeEpisode.text = item.episode.toString()
                tvEpisodeSeason.text = item.season.toString()
                tvEpisodeDate.text = item.airDate

                Glide.with(ivEpisode).load(DetailsFragment.showImg).into(ivEpisode)
            }

        }

    }

    class Comparator: DiffUtil.ItemCallback<Episode>() {
        override fun areItemsTheSame(oldItem: Episode, newItem: Episode): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Episode, newItem: Episode): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeViewHolder {
        return EpisodeViewHolder(ItemEpisodeBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: EpisodeViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    fun setImage(image: String) {

    }


}