package com.example.hw2_m6_2.character

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.hw2_m6_2.data.Character
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hw2_m6_2.R
import com.example.hw2_m6_2.databinding.ItemCharacterBinding
import com.example.hw2_m6_2.di.loadImage

class CharacterAdapter (
    var onClick:(position: Int) -> Unit
) : ListAdapter<Character, CharacterAdapter.CharacterViewHolder>(
    DIFF_UTIL_CALL_BACK
) {

    companion object {
        private val DIFF_UTIL_CALL_BACK = object : DiffUtil.ItemCallback<Character>() {
            override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
                return oldItem.url == newItem.url
            }

            override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val binding = ItemCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharacterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val character = getItem(position)
        holder.bind(character)
        holder.itemView.setOnClickListener {
            onClick(character.id)
        }
    }

    inner class CharacterViewHolder(private val binding: ItemCharacterBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(character: Character) {
            binding.apply {
                tvName.text = character.name
                tvStatus.text = character.status
                tvSpecies.text = character.species
                tvGender.text = character.gender
                ivCharacter.loadImage(character.image)

                val dot = when(character.status){
                    "Alive" -> R.drawable.ic_green_dot
                    "Dead" -> R.drawable.ic_red_dot
                    else -> R.drawable.ic_grey_dot
                }
                dotIndicator.setImageResource(dot)
            }
        }
    }
}