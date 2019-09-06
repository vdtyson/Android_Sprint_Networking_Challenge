package com.versilistyson.networkingsprint.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.versilistyson.networkingsprint.model.PokemonList

class PokemonListAdapter(var listOfPokemon: PokemonList) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    override fun getItemCount(): Int {
       return listOfPokemon.pokeList.size
    }

}