package exirium.pe.pokedex.database.util

import androidx.room.TypeConverter
import exirium.pe.pokedex.database.model.SpritesEntity
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class Converters {
    private val json = Json {
        allowStructuredMapKeys = true
        ignoreUnknownKeys = true
        coerceInputValues = true
    }

    @TypeConverter
    fun fromString(value: String): List<String> {
        return json.decodeFromString(value)
    }

    @TypeConverter
    fun fromList(list: List<String>): String {
        return json.encodeToString(list)
    }


    @TypeConverter
    fun spritesEntityToJson(value: SpritesEntity): String {
        return json.encodeToString(value)
    }

    @TypeConverter
    fun jsonToSpritesEntity(value: String): SpritesEntity {
        return json.decodeFromString(value)
    }
}