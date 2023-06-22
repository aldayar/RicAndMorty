package com.example.ricandmorty.ui.fragmet.locationfragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.ricandmorty.data.model.Location
import com.example.ricandmorty.databinding.ItemLocationBinding
import androidx.recyclerview.widget.RecyclerView.ViewHolder



class LocationAdapter : ListAdapter<Location, LocationAdapter.LocationViewHolder>(LocationDiffCallBack()) {

    class LocationViewHolder(private val binding: ItemLocationBinding) : ViewHolder(binding.root)   {
        fun bind(model: Location?) {
            binding.itemTvDimension.text = model?.dimension
            binding.itemTvEpisode.text = model?.episode
            binding.itemTvName.text = model?.name

        }

    }

    class LocationDiffCallBack: DiffUtil.ItemCallback<Location>() {
        override fun areItemsTheSame(oldItem: Location, newItem: Location): Boolean {
            return oldItem==newItem
        }

        override fun areContentsTheSame(oldItem: Location, newItem: Location): Boolean {
            return oldItem==newItem
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)= LocationViewHolder(
        ItemLocationBinding.inflate(LayoutInflater.from(parent.context),parent,false)
    )

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        val model = getItem(position)
        holder.bind(model)
    }
}