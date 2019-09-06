package com.versilistyson.networkingsprint.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Adapter
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.versilistyson.networkingsprint.GetPokemon
import com.versilistyson.networkingsprint.R
import com.versilistyson.networkingsprint.adapter.PokemonListAdapter
import com.versilistyson.networkingsprint.model.Pokemon
import com.versilistyson.networkingsprint.model.PokemonList
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity(), Callback<List<Pokemon>> {
    companion object {
        var filteredPokemonList: MutableList<Pokemon> = arrayListOf()
        var favoritePokemonList: MutableList<Pokemon> = arrayListOf()
    }
    private lateinit var pokemonAdapter: PokemonListAdapter


    override fun onFailure(call: Call<List<Pokemon>>, t: Throwable) {
        Toast.makeText(this,"The call failed", Toast.LENGTH_SHORT).show()
    }

    override fun onResponse(call: Call<List<Pokemon>>, response: Response<List<Pokemon>>) {
        if (response.isSuccessful) {
            if(filteredPokemonList.isNotEmpty()) {

                for (i in 0 until filteredPokemonList.size -1) {
                    filteredPokemonList.removeAt(i)
                }

            }
                filteredPokemonList = response.body()!!.toMutableList()
                pokemonAdapter.update(filteredPokemonList)

        } else {
            Toast.makeText(this, "was not successful", Toast.LENGTH_SHORT)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (favoritePokemonList.isNotEmpty() ) {
            initRecyclerView(favoritePokemonList)
        }



        pokemon_search_view.setOnSearchClickListener {
            val retriever = GetPokemon.create()
            retriever.getPokemon().enqueue(this)
        }
    }

    private fun initRecyclerView(pokemonList: List<Pokemon>) {
        rv_pokemon.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            pokemonAdapter = PokemonListAdapter(pokemonList)
            adapter = pokemonAdapter
        }
    }
}
