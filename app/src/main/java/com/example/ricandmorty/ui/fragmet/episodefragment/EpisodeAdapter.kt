package com.example.ricandmorty.ui.fragmet.episodefragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.ricandmorty.data.model.Episodes
import com.example.ricandmorty.databinding.ItemEpisodeBinding

class EpisodeAdapter :
    androidx.recyclerview.widget.ListAdapter<Episodes, EpisodeAdapter.EpisodeViewHolder>(
        EpisodeDiffCAllBack()
    ) {


    class EpisodeViewHolder(private val binding: ItemEpisodeBinding) : ViewHolder(binding.root) {
        fun bind(model: Episodes?) {
            binding.itemTvLocation.text = model?.name
            binding.itemTvEpisode.text = model?.episode
            binding.itemTvUrl.text = model?.url
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = EpisodeViewHolder(
        ItemEpisodeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )


    override fun onBindViewHolder(holder: EpisodeViewHolder, position: Int) {
        val model = getItem(position)
        holder.bind(model)
    }


    class EpisodeDiffCAllBack : DiffUtil.ItemCallback<Episodes>() {
        override fun areItemsTheSame(oldItem: Episodes, newItem: Episodes): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Episodes, newItem: Episodes): Boolean {
            return oldItem == newItem
        }

    }
}