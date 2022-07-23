package com.shahriyor.android_imperative.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.shahriyor.android_imperative.R
import com.shahriyor.android_imperative.ui.activity.DetailsActivity
import com.shahriyor.android_imperative.databinding.ItemTvShortBinding
import com.shahriyor.android_imperative.model.TVShow

class TVShortAdapter(var items: ArrayList<String>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_tv_short, parent, false)
        return TVShortViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val tvShort = items[position]
        if (holder is TVShortViewHolder) {
            Glide.with(holder.binding.ivShort).load(tvShort).into(holder.binding.ivShort)
        }
    }

    inner class TVShortViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemTvShortBinding.bind(view)
    }

    fun setData(list: ArrayList<String>) {
        items.addAll(list)
        notifyDataSetChanged()
    }




}