package exirium.pe.pokedex.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Entity(tableName = "sprites")
@Serializable
data class SpritesEntity(
    @PrimaryKey(autoGenerate = true) val id: Int? = 0,
    val backDefault: String?,
    val backFemale: String?,
    val backShiny: String?,
    val backShinyFemale: String?,
    val frontDefault: String?,
    val frontFemale: String?,
    val frontShiny: String?,
    val frontShinyFemale: String?
)

fun SpritesEntity.asDomain(): exirium.pe.pokedex.data.model.Sprites = exirium.pe.pokedex.data.model.Sprites(
    backDefault, backFemale, backShiny, backShinyFemale, frontDefault, frontFemale, frontShiny, frontShinyFemale
)