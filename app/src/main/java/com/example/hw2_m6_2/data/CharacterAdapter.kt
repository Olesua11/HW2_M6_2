package com.example.hw2_m6_2.data

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hw2_m6_2.R
import com.example.hw2_m6_2.databinding.ItemCharacterBinding
import com.example.hw2_m6_2.di.loadImage

class CharacterAdapter(var characters: List<Character>) :
    RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder(ItemCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val character = characters[position]
        holder.bind(character)
    }

    override fun getItemCount(): Int = characters.size

    class CharacterViewHolder(private val binding: ItemCharacterBinding) : RecyclerView.ViewHolder(binding.root) {
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