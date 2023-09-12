package exirium.pe.pokedex.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Pokedex(
    @SerialName("pokemon_entries")val pokemonEntries: List<PokemonEntries>
)
