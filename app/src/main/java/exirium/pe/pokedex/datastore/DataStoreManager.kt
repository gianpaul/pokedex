package exirium.pe.pokedex.datastore

import kotlinx.coroutines.flow.Flow

interface DataStoreManager {
    suspend fun setDataLoaded(value: Boolean)
    suspend fun isDataLoaded(): Flow<Boolean>
}