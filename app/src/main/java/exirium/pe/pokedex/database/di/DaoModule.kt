package exirium.pe.pokedex.database.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import exirium.pe.pokedex.database.PokedexDatabase
import exirium.pe.pokedex.database.dao.PokedexDao

@Module
@InstallIn(SingletonComponent::class)
object DaoModule {
    @Provides
    fun providesPokedexDao(
        database: PokedexDatabase,
    ): PokedexDao = database.pokedexDao()
}