package com.versilistyson.networkingsprint.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.versilistyson.networkingsprint.GetPokemon
import com.versilistyson.networkingsprint.R
import com.versilistyson.networkingsprint.adapter.PokemonListAdapter
import com.versilistyson.networkingsprint.model.Pokemon
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity(), Callback<Pokemon> {
    companion object {
        var pokeList: MutableList<Pokemon> = arrayListOf()
        var search: MutableList<Pokemon> = arrayListOf()
        var favoritesList: MutableList<Pokemon> = arrayListOf()
    }

    private lateinit var pokemonAdapter: PokemonListAdapter


    override fun onFailure(call: Call<Pokemon>, t: Throwable) {
        Toast.makeText(this, "The call failed", Toast.LENGTH_SHORT).show()
    }

    override fun onResponse(call: Call<Pokemon>, response: Response<Pokemon>) {
        if (response.isSuccessful) {
            search.clear()
            search.add(response.body()!!)
            if (!pokeList.contains(response.body()!!)) {
                pokeList.add(response.body()!!)
            }
            pokemonAdapter.update(search)
        } else {
            Toast.makeText(this, "was not successful", Toast.LENGTH_SHORT)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecyclerView(pokeList)


        search_main.setOnClickListener {
            val input: String = pokemon_search_view.text.toString()
            if (!input.isBlank() || !input.isEmpty()) {
                val retriever = GetPokemon.create()
                retriever.getPokemonById(input.toInt()).enqueue(this)
            }
        }

        history.setOnClickListener {
            pokemonAdapter.update(pokeList)
        }

        favorites.setOnClickListener {
            pokemonAdapter.update(favoritesList)
        }
    }
    fun initRecyclerView(pokemonList: List<Pokemon>) {
        rv_pokemon.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            pokemonAdapter = PokemonListAdapter(pokemonList)
            adapter = pokemonAdapter
        }
    }
}
