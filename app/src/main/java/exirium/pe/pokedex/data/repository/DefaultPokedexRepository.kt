package exirium.pe.pokedex.data.repository

import exirium.pe.pokedex.data.model.PokemonDetail
import exirium.pe.pokedex.data.model.asEntity
import exirium.pe.pokedex.database.dao.PokedexDao
import exirium.pe.pokedex.database.model.asDomain
import exirium.pe.pokedex.network.PokedexDataSource
import exirium.pe.pokedex.network.model.asDomain
import exirium.pe.pokedex.network.model.numberFromUrl
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DefaultPokedexRepository @Inject constructor(
    private val pokedexDataSource: PokedexDataSource,
    private val pokemonDao: PokedexDao
) : PokedexRepository {
    override suspend fun insertOrReplacePokemons() {
        coroutineScope {
            try {
                val pokemons = pokedexDataSource.getPokedexByRegion(region = REGION_GALAR)

                val chunks = pokemons.chunked(10)

                for (chunk in chunks) {
                    chunk.map { pokemon ->
                        async {
                            fetchPokemonDetail(
                                entryNumber = pokemon.entryNumber.toString(),
                                number = pokemon.pokemonSpecies.numberFromUrl().toString()
                            )
                        }
                    }.awaitAll()
                    delay(500)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override fun getPokemonList(): Flow<List<PokemonDetail>> {
        return pokemonDao.getPokemonList().map { pokemonList ->
            if (pokemonList.isEmpty()) {
                emptyList()
            } else {
                pokemonList.map { pokemon ->
                    PokemonDetail(
                        name = pokemon.name,
                        types = pokemon.types,
                        abilities = pokemon.abilities,
                        number = pokemon.id,
                        height = pokemon.height,
                        weight = pokemon.weight,
                        numberWorld = pokemon.numberWorld,
                        sprites = pokemon.sprites.asDomain(),
                        urlImage = pokemon.urlImage,
                        urlShinyImage = pokemon.urlShinyImage,
                        baseExperience = pokemon.baseExperience
                    )
                }
            }
        }
    }

    private suspend fun fetchPokemonDetail(entryNumber: String, number: String) {
        val pokemon = pokedexDataSource.getPokemonInfo(number = number)
        val paddedString = number.padStart(3, '0')
        val urlImage = "$BASE_URL_IMAGE${paddedString}.gif"
        val urlShinyImage = "$BASE_URL_SHINY_IMAGE${paddedString}.gif"
        val pokemonDetail = PokemonDetail(
            name = pokemon.name,
            types = pokemon.types.map {
                it.type.name
            },
            number = pokemon.id.toString(),
            height = pokemon.height,
            weight = pokemon.weight,
            sprites = pokemon.sprites.asDomain(),
            abilities = pokemon.abilities.map {
                it.ability.name
            },
            urlImage = urlImage,
            numberWorld = number,
            urlShinyImage = urlShinyImage,
            baseExperience = pokemon.baseExperience
        )
        pokemonDao.insertOrReplacePokemon(pokemonDetail.asEntity(number = entryNumber, urlImage = urlImage))
    }

    companion object {
        private const val BASE_URL_IMAGE = "https://pokemon-project.com/pokedex/img/sprite/EspadaEscudo/Animados/"
        private const val BASE_URL_SHINY_IMAGE = "https://pokemon-project.com/pokedex/img/sprite/EspadaEscudo/Animados/Shiny/"
        private const val REGION_GALAR = "27"
    }
}