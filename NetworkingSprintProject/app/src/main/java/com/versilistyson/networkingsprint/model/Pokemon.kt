package com.versilistyson.networkingsprint.model

data class Pokemon(
    var favorited: Boolean,
    val abilities: List<Ability>,
    val id: Int,
    val name: String,
    val sprites: Sprites,
    val types: List<Type>
)
class PokemonList (val pokeList: MutableList<Pokemon>)