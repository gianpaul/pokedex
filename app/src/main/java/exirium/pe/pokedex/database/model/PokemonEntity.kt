package exirium.pe.pokedex.database.model

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import exirium.pe.pokedex.database.util.Converters

@Entity(
    tableName = "pokemon",
    indices = [Index(value = ["id"])]
)
@TypeConverters(Converters::class)
data class PokemonEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val types: List<String>,
    val abilities: List<String>,
    val sprites: SpritesEntity,
    val height: Int,
    val weight: Int,
    val numberWorld: String,
    val urlImage: String,
    val urlShinyImage: String,
    val baseExperience: Int
)