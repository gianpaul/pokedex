package exirium.pe.pokedex.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DefaultDataStoreManager@Inject constructor(
    private val dataStore: DataStore<Preferences>
) : DataStoreManager {
    private val dataLoadedKey = booleanPreferencesKey("data_loaded")


    override suspend fun setDataLoaded(value: Boolean) {
        dataStore.edit { prefs ->
            prefs[dataLoadedKey] = value
        }
    }

    override suspend fun isDataLoaded(): Flow<Boolean> {
        return dataStore.data.map { prefs ->
            prefs[dataLoadedKey] ?: false
        }
    }

}