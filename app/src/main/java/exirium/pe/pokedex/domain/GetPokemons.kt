package exirium.pe.pokedex.domain

import exirium.pe.pokedex.data.model.PokemonDetail
import exirium.pe.pokedex.data.repository.PokedexRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetPokemons @Inject constructor(
    private val pokedexRepository: PokedexRepository
) {
    operator fun invoke(): Flow<List<PokemonDetail>> =
        pokedexRepository.getPokemonList().map { list ->
            list.sortedBy { it.number.toInt() }
        }
}