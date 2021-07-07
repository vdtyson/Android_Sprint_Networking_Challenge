package com.versilistyson.networkingsprint.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.squareup.picasso.Picasso
import com.versilistyson.networkingsprint.R
import com.versilistyson.networkingsprint.adapter.PokemonListAdapter
import com.versilistyson.networkingsprint.model.Pokemon
import com.versilistyson.networkingsprint.ui.MainActivity.Companion.pokeList
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val pokemonName = intent.getStringExtra(PokemonListAdapter.POKEMONNAME)
        val pokemonTypes = intent.getStringExtra(PokemonListAdapter.POKEMONTYPE)
        val pokemonPic = intent.getStringExtra(PokemonListAdapter.POKEMONPIC)
        val pokemonID = intent.getStringExtra(PokemonListAdapter.POKEMONID)
        val pokemonAbilities = intent.getStringExtra(PokemonListAdapter.POKEMONABILITY)
        Picasso.get()
            .load(pokemonPic)
            .resize(200,200)
            .centerCrop()
            .into(detail_iv)
        detail_id.text = pokemonID
        detail_ability.text = pokemonAbilities
        detail_name.text = pokemonName
        detail_type.text = pokemonTypes
        val pokeListClone = pokeList
        delete.setOnClickListener {
            for (i in 0 until pokeListClone.size -1) {
               if (pokeListClone[i].id == pokemonID.toInt()) {
                   pokeListClone.remove(pokeList[i])
               }
                pokeList = pokeListClone
            }
            val intent = Intent(this, MainActivity:: class.java)
            startActivity(intent)
        }
        cancel.setOnClickListener {
            val intent = Intent(this, MainActivity:: class.java)
            startActivity(intent)
        }
    }
}
