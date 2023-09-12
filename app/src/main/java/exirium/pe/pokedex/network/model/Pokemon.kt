package exirium.pe.pokedex.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Pokemon(
    val id: Int,
    val name: String,
    val types: List<Types>,
    val abilities: List<Abilities>,
    val sprites: Sprites,
    val height: Int,
    val weight: Int,
    @SerialName(value = "base_experience") val baseExperience: Int
)
