package exirium.pe.pokedex.network.model

import kotlinx.serialization.Serializable

@Serializable
data class Abilities(
    val ability: Ability,
    val slot: Int
)
