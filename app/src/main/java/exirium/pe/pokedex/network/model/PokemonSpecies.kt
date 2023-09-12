package exirium.pe.pokedex.network.model

import kotlinx.serialization.Serializable

@Serializable
data class PokemonSpecies(
    val name: String,
    val url: String
)

fun PokemonSpecies.numberFromUrl(): Int {
    val urlParts = url.split("/")
    return urlParts[urlParts.size - 2].toInt()
}
