package exirium.pe.pokedex.network.model

import kotlinx.serialization.Serializable

@Serializable
data class Ability(
    val name: String,
    val url: String
)
