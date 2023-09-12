package exirium.pe.pokedex.network.model

import kotlinx.serialization.Serializable

@Serializable
data class Types(
    val slot: Int,
    val type: Type
)