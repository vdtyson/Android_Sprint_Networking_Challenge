package com.versilistyson.networkingsprint.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Switch
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.versilistyson.networkingsprint.R
import com.versilistyson.networkingsprint.model.Pokemon
import com.versilistyson.networkingsprint.model.PokemonList
import kotlinx.android.synthetic.main.pokemon_list_cardview.view.*

class PokemonListAdapter(var items: List<Pokemon>) : RecyclerView.Adapter<PokemonListAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val cardView = view.poke_cv
        val pokeSprite = view.cv_poke_image as ImageView
        val pokeName = view.cv_poke_name as TextView
        val pokeId = view.cv_poke_id as TextView
        val favSwitch =  view.favorite_switch as Switch
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewGroup = LayoutInflater.from(parent.context).inflate(R.layout.pokemon_list_cardview, parent,  false)
        return ViewHolder(viewGroup)
    }

    override fun getItemCount(): Int {
       return items.size
    }

    override fun onBindViewHolder(holder: PokemonListAdapter.ViewHolder, position: Int) {
    }

    fun update(modelList:List<Pokemon>){
        items = modelList
        notifyDataSetChanged()
    }


}