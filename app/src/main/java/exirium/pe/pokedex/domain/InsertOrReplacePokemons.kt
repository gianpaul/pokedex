package exirium.pe.pokedex.domain

import exirium.pe.pokedex.data.repository.PokedexRepository
import javax.inject.Inject

class InsertOrReplacePokemons @Inject constructor(
    private val pokedexRepository: PokedexRepository
) {
    suspend operator fun invoke() = pokedexRepository.insertOrReplacePokemons()
}