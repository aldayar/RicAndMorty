package com.example.ricandmorty.ui.fragmet

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.ricandmorty.R
import com.example.ricandmorty.data.model.Characters
import com.example.ricandmorty.databinding.ItemCharactersBinding

class CharacterAdapter(val listener: CharacterDiffCallback.OnClickListener) :
    ListAdapter<Characters, CharacterAdapter.CharacterViewHolder>(CharacterDiffCallback()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = CharacterViewHolder(
        ItemCharactersBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )
    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val model = getItem(position)
        holder.onBind(model, listener)
    }
    class CharacterViewHolder(private val binding: ItemCharactersBinding) :
        ViewHolder(binding.root) {
        @SuppressLint("ResourceAsColor")
        fun onBind(model: Characters?, listener: CharacterDiffCallback.OnClickListener) {
            itemView.setOnClickListener {
                listener.
                onClick(model!!.id)
            }
            binding.itemTvMain.text = model?.name
            Glide.with(binding.root).load(model?.image).centerCrop().into(binding.itemIgm)
            if (model?.gender == "Male") {
                binding.itemTvMain.setTextColor(
                    ContextCompat.getColor(
                        binding.root.context,
                        R.color.black
                    )
                )
            }
            if (model?.gender == "Female") {
                binding.itemTvMain.setTextColor(ContextCompat.getColor(binding.root.context,R.color.purple))
            }
        }
    }
}
class CharacterDiffCallback : DiffUtil.ItemCallback<Characters>() {
    override fun areItemsTheSame(oldItem: Characters, newItem: Characters): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Characters, newItem: Characters): Boolean {
        return oldItem == newItem
    }

    interface OnClickListener {
        fun onClick(model: Int)
    }
}
