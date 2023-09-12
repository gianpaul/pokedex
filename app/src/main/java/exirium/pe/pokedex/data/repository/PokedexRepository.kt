package exirium.pe.pokedex.data.repository

import exirium.pe.pokedex.data.model.PokemonDetail
import kotlinx.coroutines.flow.Flow

interface PokedexRepository {
    suspend fun insertOrReplacePokemons()
    fun getPokemonList(): Flow<List<PokemonDetail>>
}