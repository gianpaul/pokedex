package exirium.pe.pokedex.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import exirium.pe.pokedex.database.dao.PokedexDao
import exirium.pe.pokedex.database.model.PokemonEntity
import exirium.pe.pokedex.database.model.SpritesEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

@Database(
    entities = [
        PokemonEntity::class,
        SpritesEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class PokedexDatabase : RoomDatabase() {
    abstract fun pokedexDao(): PokedexDao

    companion object {
        @Volatile
        private var instance: PokedexDatabase? = null

        fun getInstance(context: Context): PokedexDatabase = instance ?: synchronized(this) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context): PokedexDatabase =
            Room.databaseBuilder(context, PokedexDatabase::class.java, "pokedex-database")
                .fallbackToDestructiveMigration()
                .build()

        suspend fun clean(context: Context) = coroutineScope {
            launch(Dispatchers.IO) {
                getInstance(context).clearAllTables()
            }
        }
    }
}