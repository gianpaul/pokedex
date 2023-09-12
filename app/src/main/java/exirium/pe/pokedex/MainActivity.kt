package exirium.pe.pokedex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint
import exirium.pe.pokedex.feature.pokemon_detail.PokemonDetailScreen
import exirium.pe.pokedex.ui.theme.PokedexTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokemonDetailScreen()
        }
    }
}



@Preview(showBackground = true, device = Devices.PIXEL_2)
@Composable
fun GreetingPreview() {
    PokedexTheme {
        PokemonDetailScreen()
    }
}