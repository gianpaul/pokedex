package exirium.pe.pokedex.feature.pokemon_detail

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import exirium.pe.pokedex.data.model.PokemonDetail
import exirium.pe.pokedex.datastore.DefaultDataStoreManager
import exirium.pe.pokedex.domain.GetPokemons
import exirium.pe.pokedex.domain.InsertOrReplacePokemons
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonDetailViewModel @Inject constructor(
    private val getPokemons: GetPokemons,
    private val insertOrReplacePokemons: InsertOrReplacePokemons,
    private val dataStoreManager: DefaultDataStoreManager
) : ViewModel() {
    private val isDataLoaded = mutableStateOf(false)

    private val _uiState: MutableStateFlow<PokemonDetailUiState> =
        MutableStateFlow(PokemonDetailUiState.Loading)
    val uiState: StateFlow<PokemonDetailUiState> = _uiState

    init {
        loadData()
    }

    private fun loadData() = viewModelScope.launch {
        val loadedFromDataStore = dataStoreManager.isDataLoaded().first()
        delay(3000)

        if (! loadedFromDataStore) {
            _uiState.value = PokemonDetailUiState.Loading

            insertOrReplacePokemons()

            dataStoreManager.setDataLoaded(true)

            val pokemons = getPokemons().first()
            _uiState.value = PokemonDetailUiState.Pokemons(pokemons)

            isDataLoaded.value = true
        } else {
            val pokemons = getPokemons().first()
            _uiState.value = PokemonDetailUiState.Pokemons(pokemons)
            isDataLoaded.value = true
        }
    }
}

sealed interface PokemonDetailUiState {
    object Loading : PokemonDetailUiState
    data class Pokemons(val pokemons: List<PokemonDetail>) : PokemonDetailUiState
    data class Error(val message: String) : PokemonDetailUiState
}