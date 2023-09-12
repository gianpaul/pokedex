package exirium.pe.pokedex.data.model

import exirium.pe.pokedex.database.model.PokemonEntity

data class PokemonDetail(
    val name: String,
    val types: List<String>,
    val abilities: List<String>,
    val number: String,
    val numberWorld: String,
    val height: Int,
    val urlImage: String,
    val urlShinyImage: String,
    val sprites: Sprites,
    val weight: Int,
    val baseExperience: Int
)

data class Sprites(
    val backDefault: String?,
    val backFemale: String?,
    val backShiny: String?,
    val backShinyFemale: String?,
    val frontDefault: String?,
    val frontFemale: String?,
    val frontShiny: String?,
    val frontShinyFemale: String?
) {
    fun toMap(): Map<String, String> {
        return mapOf(
            "backDefault" to (backDefault ?: ""),
            "backFemale" to (backFemale ?: ""),
            "backShiny" to (backShiny ?: ""),
            "backShinyFemale" to (backShinyFemale ?: ""),
            "frontDefault" to (frontDefault ?: ""),
            "frontFemale" to (frontFemale ?: ""),
            "frontShiny" to (frontShiny ?: ""),
            "frontShinyFemale" to (frontShinyFemale ?: "")
        )
    }
}

fun Sprites.asEntity(): exirium.pe.pokedex.database.model.SpritesEntity =
    exirium.pe.pokedex.database.model.SpritesEntity(
        backDefault = backDefault,
        backFemale =backFemale,
        backShiny = backShiny,
        backShinyFemale = backShinyFemale,
        frontDefault = frontDefault,
        frontFemale = frontFemale,
        frontShiny = frontShiny,
        frontShinyFemale = frontShinyFemale
    )

fun PokemonDetail.asEntity(number: String, urlImage: String): PokemonEntity {
    return PokemonEntity(
        id = number,
        name = name,
        types = types,
        height = height,
        weight = weight,
        abilities = abilities,
        urlImage = urlImage,
        urlShinyImage = urlShinyImage,
        numberWorld = numberWorld,
        sprites = sprites.asEntity(),
        baseExperience = baseExperience
    )
}
