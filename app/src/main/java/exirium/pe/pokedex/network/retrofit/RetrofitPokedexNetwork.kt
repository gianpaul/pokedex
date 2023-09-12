package exirium.pe.pokedex.network.retrofit

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import exirium.pe.pokedex.network.PokedexDataSource
import exirium.pe.pokedex.network.model.Pokedex
import exirium.pe.pokedex.network.model.Pokemon
import exirium.pe.pokedex.network.model.PokemonEntries
import kotlinx.serialization.json.Json
import okhttp3.Call
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path
import javax.inject.Inject
import javax.inject.Singleton

private interface RetrofitPokedexNetworkApi {
    @GET(value = "pokedex/{region}")
    suspend fun getPokedexByRegion(@Path("region") region: String): Response<Pokedex>

    @GET(value = "pokemon/{number}")
    suspend fun getPokemonByNumber(@Path("number") name: String): Response<Pokemon>
}

private const val POKEDEX_BASE_URL = "https://pokeapi.co/api/v2/"

@Singleton
class RetrofitPokedexNetwork @Inject constructor(
    networkJson: Json,
    okhttpCallFactory: Call.Factory,
) : PokedexDataSource {

    private val networkApi =
        Retrofit.Builder().baseUrl(POKEDEX_BASE_URL).callFactory(okhttpCallFactory)
            .addConverterFactory(
                networkJson.asConverterFactory("application/json".toMediaType()),
            ).build().create(RetrofitPokedexNetworkApi::class.java)

    override suspend fun getPokedexByRegion(region: String): List<PokemonEntries> = networkApi
        .getPokedexByRegion(region).body()?.pokemonEntries
        ?: throw Exception("Pokedex not found")

    override suspend fun getPokemonInfo(pokemonName: String): Pokemon =
        networkApi.getPokemonByNumber(pokemonName).body() ?: throw Exception("Pokemon not found")

}