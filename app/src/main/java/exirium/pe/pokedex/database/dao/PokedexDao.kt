package exirium.pe.pokedex.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import exirium.pe.pokedex.database.model.PokemonEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PokedexDao {
    @Query(value = "SELECT * FROM pokemon")
    fun getPokemonList(): Flow<List<PokemonEntity>>

    @Upsert
    suspend fun insertOrReplacePokemon(pokemon: PokemonEntity)
}