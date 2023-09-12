package exirium.pe.pokedex.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import exirium.pe.pokedex.data.repository.DefaultPokedexRepository
import exirium.pe.pokedex.data.repository.PokedexRepository
import exirium.pe.pokedex.datastore.DataStoreManager
import exirium.pe.pokedex.datastore.DefaultDataStoreManager
import exirium.pe.pokedex.network.PokedexDataSource
import exirium.pe.pokedex.network.retrofit.RetrofitPokedexNetwork

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {
    @Binds
    fun bindsPokedexRepository(
        defaultPokedexRepository: DefaultPokedexRepository
    ): PokedexRepository

    @Binds
    fun bindsPokedexDataSource(
        retrofitPokedexNetwork: RetrofitPokedexNetwork
    ): PokedexDataSource

    @Binds
    fun bindsDataStoreManager(
        defaultDataStoreManager: DefaultDataStoreManager
    ): DataStoreManager
}