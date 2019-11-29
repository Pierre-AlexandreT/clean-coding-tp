package fr.appsolute.tp.ui.widget.holder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import fr.appsolute.tp.R
import fr.appsolute.tp.data.model.Character
import kotlinx.android.synthetic.main.holder_character.view.*

class CharacterViewHolder private constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(model: Character, onClick: (View, Character) -> Unit) {
        itemView.apply {
            this.setOnClickListener { onClick(it, model) }
            this.holder_character_full_name.text = model.name
            this.holder_character_location.text = model.location.name
            Glide
                .with(this)
                .load(model.image)
                .into(this.holder_character_avatar)
        }
    }

    companion object {
        fun create(parent: ViewGroup): CharacterViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(
                R.layout.holder_character,
                parent,
                false
            )
            return CharacterViewHolder(view)
        }
    }

}