package com.versilistyson.networkingsprint.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Switch
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.versilistyson.networkingsprint.R
import com.versilistyson.networkingsprint.model.Pokemon
import com.versilistyson.networkingsprint.model.PokemonList
import com.versilistyson.networkingsprint.ui.MainActivity
import com.versilistyson.networkingsprint.ui.MainActivity.Companion.filteredPokemonList
import kotlinx.android.synthetic.main.pokemon_list_cardview.view.*

class PokemonListAdapter(var items: List<Pokemon>) : RecyclerView.Adapter<PokemonListAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var cardView = view.poke_cv
        var pokeSprite = view.cv_poke_image as ImageView
        var pokeName = view.cv_poke_name as TextView
        var pokeId = view.cv_poke_id as TextView
        var favSwitch =  view.favorite_switch as Switch
        fun onClickListener(position: Pokemon) {
            favSwitch.setOnClickListener {
                if(favSwitch.isChecked && !filteredPokemonList.contains())
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewGroup = LayoutInflater.from(parent.context).inflate(R.layout.pokemon_list_cardview, parent,  false)
        return ViewHolder(viewGroup)
    }

    override fun getItemCount(): Int {
       return items.size
    }

    override fun onBindViewHolder(holder: PokemonListAdapter.ViewHolder, position: Int) {
        holder.pokeId.text = items[position].id.toString()
        holder.pokeName.text = items[position].name
        Picasso.get()
            .load(items[position].sprites.front_default)
            .resize(100,100)
            .centerCrop()
            .into(holder.pokeSprite)
        holder
    }

    fun update(modelList:List<Pokemon>){
        items = modelList
        notifyDataSetChanged()
    }


}