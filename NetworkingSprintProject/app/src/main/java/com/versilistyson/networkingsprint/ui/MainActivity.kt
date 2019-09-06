package com.versilistyson.networkingsprint.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.versilistyson.networkingsprint.R
import com.versilistyson.networkingsprint.model.Pokemon
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Callback


class MainActivity : AppCompatActivity(), Callback<List<Pokemon>> {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pokemon_search_view.onQ
    }
}
