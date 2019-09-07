package com.versilistyson.networkingsprint.adapter

import android.app.Activity
import android.content.Intent
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Switch
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.versilistyson.networkingsprint.R
import com.versilistyson.networkingsprint.model.Pokemon
import com.versilistyson.networkingsprint.model.PokemonList
import com.versilistyson.networkingsprint.ui.DetailActivity
import com.versilistyson.networkingsprint.ui.MainActivity
import kotlinx.android.synthetic.main.pokemon_list_cardview.view.*
import java.io.Serializable

class PokemonListAdapter(var items: List<Pokemon>) : RecyclerView.Adapter<PokemonListAdapter.ViewHolder>() {
    companion object {
        const val POKEMONNAME = "POKEMONNAME"
        const val POKEMONID = "POKEMONID"
        const val POKEMONTYPE = "POKEMONTYPE"
        const val POKEMONPIC = "POKEMONPICK"
        const val POKEMONABILITY = "POKEMONABILITY"
    }
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var pokemonButton = view.cv_detail_button as Button
        var cardView = view.poke_cv
        var pokeSprite = view.cv_poke_image as ImageView
        var pokeName = view.cv_poke_name as TextView
        var pokeId = view.cv_poke_id as TextView
        var favSwitch =  view.favorite_switch as Switch


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewGroup = LayoutInflater.from(parent.context).inflate(R.layout.pokemon_list_cardview, parent,  false)
        val holder = ViewHolder(viewGroup)
        return holder
    }

    override fun getItemCount(): Int {
       return items.size
    }

    override fun onBindViewHolder(holder: PokemonListAdapter.ViewHolder, position: Int) {
        val pokemon = items[position]
        val context = holder.cardView.context
        holder.pokeId.text = items[position].id.toString()
        holder.pokeName.text = items[position].name
        Picasso.get()
            .load(items[position].sprites.front_default)
            .resize(100,100)
            .centerCrop()
            .into(holder.pokeSprite)
        holder.favSwitch.setOnClickListener {
            if(!pokemon.favorited && holder.favSwitch.isChecked) {
                pokemon.favorited = true
            } else if (pokemon.favorited && !holder.favSwitch.isChecked) {
                pokemon.favorited = false
            }
        }
        holder.pokemonButton.setOnClickListener {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(POKEMONABILITY, pokemon.abilities.toString())
            intent.putExtra(POKEMONID,pokemon.id.toString())
            intent.putExtra(POKEMONPIC, pokemon.sprites.front_default)
            intent.putExtra(POKEMONNAME,pokemon.name)
            intent.putExtra(POKEMONTYPE, pokemon.types.toString())
            context.startActivity(intent)
        }
    }

    fun update(modelList:List<Pokemon>){
        items = modelList
        notifyDataSetChanged()
    }


}
interface UserClickCallbacks {
    fun onButtonClick(pokemon: Pokemon)
    fun onSwitchClick(pokemon: Pokemon)
}