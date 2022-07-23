package com.shahriyor.android_imperative.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.shahriyor.android_imperative.R
import com.shahriyor.android_imperative.databinding.ItemTvShowBinding
import com.shahriyor.android_imperative.model.TVShow

class TVShowAdapter : ListAdapter<TVShow, TVShowAdapter.TVShowViewHolder>(Comparator()) {

    var onItemClick: ((TVShow, ImageView) -> Unit)? = null

    class Comparator : DiffUtil.ItemCallback<TVShow>() {
        override fun areItemsTheSame(oldItem: TVShow, newItem: TVShow): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: TVShow, newItem: TVShow): Boolean {
            return oldItem == newItem
        }

    }

    inner class TVShowViewHolder(private val binding: ItemTvShowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: TVShow?) {
            binding.apply {
                tvName.text = item?.name
                tvType.text = item?.network
                Glide.with(ivMovie).load(item?.image_thumbnail_path)
                    .placeholder(R.drawable.ic_launcher_background).into(ivMovie)

                ViewCompat.setTransitionName(ivMovie, item?.name)
                root.setOnClickListener {
                    onItemClick?.invoke(item!!, ivMovie)
                }
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TVShowViewHolder {
        return TVShowViewHolder(ItemTvShowBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false))
    }

    override fun onBindViewHolder(holder: TVShowViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }
}