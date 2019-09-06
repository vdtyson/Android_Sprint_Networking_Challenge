package com.versilistyson.networkingsprint.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.squareup.picasso.Picasso
import com.versilistyson.networkingsprint.R
import com.versilistyson.networkingsprint.adapter.PokemonListAdapter
import com.versilistyson.networkingsprint.model.Pokemon
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val pokemon: Pokemon = intent.getSerializableExtra(PokemonListAdapter.POKEMON) as Pokemon
        Picasso.get()
            .load(pokemon.sprites.front_default)
            .resize(200,200)
            .centerCrop()
            .into(detail_iv)
        detail_id.text = pokemon.id.toString()
        detail_ability.text = pokemon.abilities.toString()
        detail_name.text = pokemon.name
        detail_type.text = pokemon.types.toString()
        delete.setOnClickListener {
            MainActivity.pokeList.remove(pokemon)
            val intent = Intent(this, MainActivity:: class.java)
            startActivity(intent)
        }
        favorite.setOnClickListener {
            if (!MainActivity.favoritesList.contains(pokemon)) {
                pokemon.favorited = true
            }
        }
        cancel.setOnClickListener {
            val intent = Intent(this, MainActivity:: class.java)
            startActivity(intent)
        }
    }
}
