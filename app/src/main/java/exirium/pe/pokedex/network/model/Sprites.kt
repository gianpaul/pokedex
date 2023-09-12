package exirium.pe.pokedex.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Sprites(
    @SerialName(value = "back_default") val backDefault: String? = "",
    @SerialName(value = "back_female") val backFemale: String? = "",
    @SerialName(value = "back_shiny") val backShiny: String? = "",
    @SerialName(value = "back_shiny_female") val backShinyFemale: String? = "",
    @SerialName(value = "front_default") val frontDefault: String? = "",
    @SerialName(value = "front_female") val frontFemale: String? = "",
    @SerialName(value = "front_shiny") val frontShiny: String? = "",
    @SerialName(value = "front_shiny_female") val frontShinyFemale: String? = "",
)

fun Sprites.asDomain(): exirium.pe.pokedex.data.model.Sprites =
    exirium.pe.pokedex.data.model.Sprites(
        backDefault,
        backFemale,
        backShiny,
        backShinyFemale,
        frontDefault,
        frontFemale,
        frontShiny,
        frontShinyFemale
    )