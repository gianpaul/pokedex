package exirium.pe.pokedex.network

import exirium.pe.pokedex.network.model.Pokemon
import exirium.pe.pokedex.network.model.PokemonEntries
import exirium.pe.pokedex.network.model.PokemonSpecies

interface PokedexDataSource {
    suspend fun getPokedexByRegion(region: String): List<PokemonEntries>
    suspend fun getPokemonInfo(number: String): Pokemon
}