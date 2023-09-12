package exirium.pe.pokedex.feature.pokemon_detail

import android.media.MediaPlayer
import android.net.Uri
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import exirium.pe.pokedex.R
import exirium.pe.pokedex.core.ui.PokemonTypeAnimation
import exirium.pe.pokedex.data.model.PokemonDetail
import kotlinx.coroutines.delay
import java.util.Locale

@Composable
fun PokemonDetailScreen(
    pokemonDetailviewModel: PokemonDetailViewModel = hiltViewModel()
) {
    val uiState by pokemonDetailviewModel.uiState.collectAsState()

    when (uiState) {
        is PokemonDetailUiState.Loading -> PokedexLoading()

        is PokemonDetailUiState.Pokemons -> {
            val pokemons = (uiState as PokemonDetailUiState.Pokemons).pokemons
            PokemonDetail(pokemons)
        }

        is PokemonDetailUiState.Error -> {
            val message = (uiState as PokemonDetailUiState.Error).message
            Text(text = message)
        }
    }
}

@Composable
fun MyStatusBarColorChanger(color: Color) {
    val systemUiController = rememberSystemUiController()
    val useDarkIcons = remember { true }

    systemUiController.setStatusBarColor(
        color = color, darkIcons = useDarkIcons
    )
}

@Composable
fun PokedexLoading() {
    MyStatusBarColorChanger(color = Color.Red)

    val pokemonFacts = listOf(
        "Pikachu was inspired by a squirrel, not a mouse.",
        "Hitmonchan and Hitmonlee are named after Jackie Chan and Bruce Lee.",
        "Wobbuffet is actually the blue tail, not the black body.",
        "Slowpoke's tail can regenerate if cut off.",
        "Originally, Pokémon was going to be called 'Capsule Monsters.'",
        "The 'Kanto' region in the Pokémon world is based on a real place in Japan.",
        "Butterfree was originally going to be the evolution of Venomoth.",
        "The Pokémon franchise is more than 25 years old.",
        "Meowth is the only Pokémon that can speak human language in the anime.",
        "The most expensive Pokémon card ever sold was a Pikachu Illustrator card.",
        "There are currently over 800 different species of Pokémon.",
        "In the original games, Pokémon cries were simple MIDI arrangements.",
        "In the anime, Ash Ketchum never ages.",
        "Clefairy was originally going to be the mascot of Pokémon.",
        "Ditto cannot transform into a Pokémon with the ability 'Imposter'.",
        "The Pokémon Farfetch’d is based on a Japanese saying.",
        "Magikarp can leap over mountains according to the Pokédex.",
        "Gengar is believed to be a shadow of Clefable.",
        "Ekans is 'snake' spelled backward, and Arbok is 'kobra' spelled backward.",
        "The Jynx controversy led to a color change in its design.",
        "In the original Pokémon Red/Blue, you could fish in the statues at gyms.",
        "Mewtwo's birthday is February 6th.",
        "The move 'Splash' is a mistranslation of the Japanese word for 'hop.'",
        "Luvdisc and Alomomola are not related by evolution.",
        "Arcanine was originally intended to be a Legendary Pokémon.",
        "Ash’s Pikachu is male.",
        "The Pokémon Company enforces a strict 'No Guns' policy in its universe.",
        "In Pokémon GO, the U.S. Pentagon was a gym.",
        "There are more than 1200 episodes of the Pokémon anime.",
        "You can complete the original games without ever getting a Pokédex.",
        "The anime episode 'Electric Soldier Porygon' caused seizures in Japanese viewers.",
        "The Exp. Share item was introduced to make leveling up easier.",
        "A Mankey once stole Ash's hat in the anime.",
        "The shortest Pokémon is Flabébé at 0.1 meters.",
        "The heaviest Pokémon is Copperajah, weighing at 650 kg.",
        "Pokémon Centers in Japan offer real medical advice.",
        "There’s a real-life island called Pokémon Island in South Korea.",
        "In the French version, Pikachu speaks with a female voice.",
        "Gotta catch 'em all' was the original Pokémon slogan.",
        "There are over 100 Pokémon-inspired drinks at Tokyo’s Pokémon Café.",
        "You can't catch other Trainer's Pokémon in the main series games.",
        "Jigglypuff’s song is the same in every language.",
        "The longest Pokémon is Wailord at 14.5 meters.",
        "Bulbasaur is the only unevolved dual-type starter Pokémon.",
        "Lapras was inspired by the Loch Ness Monster.",
        "Spinda has over 4 billion different forms due to its spot patterns.",
        "Mew was a last-minute addition to Pokémon Red and Blue.",
        "Mareep's design was inspired by a plush sheep.",
        "The Pokémon Kadabra has not appeared in the TCG for over 15 years.",
        "Chansey is a symbol of good luck and prosperity in Japan.",
        "Nidoking and Nidoqueen cannot breed, despite being part of the same evolutionary line.",
        "Drowzee is based on a tapir and a creature from Japanese folklore."
    )

    MyStatusBarColorChanger(color = Color.Red)
    val infiniteTransition = rememberInfiniteTransition()
    val progress by infiniteTransition.animateFloat(
        initialValue = 0f, targetValue = 1f, animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing), repeatMode = RepeatMode.Restart
        ), label = ""
    )
    var triviaIndex by remember { mutableStateOf(0) }

    LaunchedEffect(key1 = "trivia") {
        while (true) {
            delay(5000L)  // 10 segundos
            triviaIndex = (triviaIndex + 1) % pokemonFacts.size
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Red), contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.pokedex),
            contentDescription = "Pokedex Loading",
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 40.dp, end = 40.dp),
            contentScale = ContentScale.FillBounds
        )

        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(40.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            LinearProgressIndicator(
                progress = 1f,
                color = Color.White,
                trackColor = Color.White,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp)
            )

            LinearProgressIndicator(
                progress = progress,
                color = Color.Blue,
                trackColor = Color.Transparent,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp)
            )
        }

        Text(
            text = pokemonFacts[triviaIndex],
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(start = 40.dp, end = 40.dp),
            style = TextStyle(
                fontSize = 16.sp, color = Color.White
            ),
            textAlign = TextAlign.Start
        )
    }
}

@Composable
fun PokemonDetail(pokemons: List<PokemonDetail>) {
    val currentIndex = remember { mutableStateOf(0) }
    val currentType =
        remember { mutableStateOf(if (pokemons.isNotEmpty()) pokemons[0].types.first() else "grass") }

    MyStatusBarColorChanger(color = getTypeColor(currentType.value))

    LaunchedEffect(currentIndex.value) {
        if (pokemons.isNotEmpty()) {
            currentType.value = pokemons[currentIndex.value].types.first()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(getTypeColor(currentType.value)),
        contentAlignment = Alignment.TopCenter
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(240.dp), contentAlignment = Alignment.Center
        ) {
            if (pokemons.isNotEmpty()) {
                PokemonTypeAnimation(typeState = currentType)
            }
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize()
        ) {
            Spacer(modifier = Modifier.height(80.dp))
            if (pokemons.isNotEmpty()) {
                val currentPokemon = pokemons[currentIndex.value]
                var currentUrlImage = pokemons[currentIndex.value].urlImage
                Column(
                    modifier = Modifier
                        .height(200.dp)
                        .padding(top = 30.dp)
                ) {
                    AnimatedGIF(currentUrlImage)
                }
                PokemonSoundButton(namePokemon = currentPokemon.name)
                ImprovedInformation(name = currentPokemon.name,
                    number = currentPokemon.number,
                    height = currentPokemon.height,
                    weight = currentPokemon.weight,
                    baseExperience = currentPokemon.baseExperience,
                    types = currentPokemon.types,
                    abilities = currentPokemon.abilities,
                    sprites = currentPokemon.sprites.toMap().filter { it.value.isNotBlank() },
                    changeType = { newType ->
                        currentType.value = newType
                    })
            }
        }

        Box(
            modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                if (currentIndex.value > 0) {
                    AnimatedArrowButton(isActive = true,
                        direction = Direction.Left,
                        onClick = { currentIndex.value -= 1 })
                } else {
                    Spacer(modifier = Modifier.width(48.dp))
                }

                if (currentIndex.value < pokemons.size - 1) {
                    AnimatedArrowButton(isActive = true,
                        direction = Direction.Right,
                        onClick = { currentIndex.value += 1 })
                } else {
                    Spacer(modifier = Modifier.width(48.dp))
                }
            }
        }
    }
}

@Composable
fun PokemonSoundButton(namePokemon: String) {
    val context = LocalContext.current
    val mediaPlayer = rememberUpdatedState(MediaPlayer().apply {
        setOnPreparedListener { mp ->
            mp.start()
        }
    })
    val name = namePokemon.replaceFirstChar { it.lowercase() }

    val soundUrl = "https://play.pokemonshowdown.com/audio/cries/${name}.mp3"

    Button(
        onClick = {
            mediaPlayer.value.apply {
                reset()
                setDataSource(context, Uri.parse(soundUrl))
                prepareAsync()
            }
        },
        modifier = Modifier.padding(16.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray.copy(alpha = 0.8f))
    ) {
        Text("Play Pokémon Sound")
    }

    DisposableEffect(Unit) {
        onDispose {
            mediaPlayer.value.release()
        }
    }
}

@Composable
fun AnimatedGIF(url: String) {
    AndroidView(factory = { context ->
        ImageView(context).apply {
            Glide.with(context).load(url).diskCacheStrategy(DiskCacheStrategy.ALL).override(500)
                .into(this)
        }
    }, update = { view ->
        Glide.with(view.context).load(url).diskCacheStrategy(DiskCacheStrategy.ALL).override(500)
            .into(view)
    })
}

@Composable
fun AnimatedArrowButton(isActive: Boolean, direction: Direction, onClick: () -> Unit) {
    val scale by animateFloatAsState(
        targetValue = if (isActive) 1.2f else 1f, animationSpec = tween(
            durationMillis = 300, easing = LinearEasing
        ), label = ""
    )

    val interactionSource = remember { MutableInteractionSource() }
    val customIndication = rememberRipple(
        bounded = false, radius = 24.dp, color = Color.DarkGray.copy(alpha = 0.8f)
    )

    CompositionLocalProvider(LocalIndication provides customIndication) {
        IconButton(
            onClick = { onClick() },
            modifier = Modifier.scale(scale),
            interactionSource = interactionSource
        ) {
            Icon(
                imageVector = if (direction == Direction.Left) Icons.Default.ArrowBack else Icons.Default.ArrowForward,
                contentDescription = if (direction == Direction.Left) "Anterior" else "Siguiente",
                modifier = Modifier
                    .background(
                        color = Color.DarkGray.copy(alpha = 0.8f), shape = CircleShape
                    )
                    .padding(4.dp),
                tint = Color.White
            )
        }
    }
}

enum class Direction {
    Left, Right
}


@Composable
fun ImprovedInformation(
    name: String,
    number: String,
    height: Int,
    weight: Int,
    baseExperience: Int,
    types: List<String>,
    abilities: List<String>,
    sprites: Map<String, String>,
    changeType: (String) -> Unit
) {
    Surface(
        shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
        color = Color.White,
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp)
            ) {

                Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                    Text(
                        text = "№$number " + name.replaceFirstChar { it.uppercase() },
                        style = TextStyle(
                            fontSize = 28.sp, color = Color.Black, fontWeight = FontWeight.Bold
                        )
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier.padding(top = 4.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    types.forEach { type ->
                        TypeButton(type = type, onClick = { changeType(type) })
                        Spacer(modifier = Modifier.width(8.dp))
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    InfoColumn(
                        icon = R.drawable.ic_height, label = "Height", value = "$height dm"
                    )
                    InfoColumn(
                        icon = R.drawable.ic_balance, label = "Weight", value = "$weight hg"
                    )
                    InfoColumn(
                        icon = R.drawable.ic_experience,
                        label = "Base XP",
                        value = "$baseExperience"
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text("Abilities", style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold))

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    abilities.forEach { ability ->
                        AbilityChip(ability = ability)
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text("Sprites", style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold))

                Spacer(modifier = Modifier.height(8.dp))

                AutoScrollingLazyRow(sprites = sprites)
            }
        }
    }
}

@Composable
fun AnimatedStarButton(
    isShiny: Boolean, onClick: () -> Unit
) {
    val scale by animateFloatAsState(if (isShiny) 1.5f else 1f, label = "")
    val buttonSize = 100.dp
    Box(
        modifier = Modifier
            .size(buttonSize)
            .scale(scale), contentAlignment = Alignment.Center
    ) {
        IconButton(onClick = onClick) {
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = null,
                tint = if (isShiny) Color.Yellow else Color.Gray
            )
        }
    }
}

@Composable
fun AutoScrollingLazyRow(sprites: Map<String, String>) {
    val listState = rememberLazyListState()

    LaunchedEffect(sprites) {
        if (sprites.size > 6) {
            val delayTime = 1000L
            val maxIndex = sprites.keys.size - 1
            var currentIndex = 0

            while (true) {
                delay(delayTime)
                if (currentIndex < maxIndex) {
                    listState.animateScrollToItem(currentIndex ++)
                } else {
                    currentIndex = 0
                    listState.animateScrollToItem(currentIndex)
                }
            }
        }
    }

    SpriteList(sprites = sprites, listState = listState)
}

@Composable
fun SpriteList(sprites: Map<String, String>, listState: LazyListState) {

    LazyRow(
        state = listState, horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(sprites.keys.toList()) { spriteKey ->
            val spriteType = spriteKey.replace('_', ' ')
                .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() }
            SpriteItem(spriteType = spriteType, spriteUrl = sprites[spriteKey] ?: "")
        }
    }
}

@Composable
fun SpriteItem(spriteType: String, spriteUrl: String) {
    var showPreview by remember { mutableStateOf(false) }
    val description = spriteType.replace(Regex("([a-z])([A-Z]+)"), "$1 $2")

    Column(
        horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center
    ) {
        Image(painter = rememberImagePainter(spriteUrl),
            contentDescription = spriteType,
            modifier = Modifier
                .size(50.dp)
                .clickable {
                    showPreview = true
                })

        if (showPreview) {
            Dialog(onDismissRequest = { showPreview = false }) {
                Box(
                    contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()
                ) {
                    Card(
                        modifier = Modifier
                            .width(250.dp)
                            .height(300.dp)
                            .clip(shape = RoundedCornerShape(8.dp))
                    ) {
                        Text(
                            text = "$description",
                            modifier = Modifier
                                .padding(16.dp)
                                .align(Alignment.CenterHorizontally),
                            style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Bold)
                        )
                        Column(
                            verticalArrangement = Arrangement.SpaceBetween,
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .fillMaxSize()
                                .background(Color.White)
                        ) {
                            Image(painter = rememberImagePainter(spriteUrl),
                                contentDescription = spriteType,
                                modifier = Modifier
                                    .size(300.dp)
                                    .align(Alignment.CenterHorizontally)
                                    .clickable {
                                        showPreview = false
                                    })
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun AbilityChip(ability: String) {
    Surface(
        shape = RoundedCornerShape(12.dp),
        color = Color.DarkGray.copy(alpha = 0.8f),
        modifier = Modifier.padding(4.dp)
    ) {
        Text(
            text = ability.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() },
            style = TextStyle(color = Color.White, fontSize = 14.sp),
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Composable
fun TypeButton(type: String, onClick: () -> Unit = {}) {
    val resId = getTypeDrawableId(type)

    Button(
        onClick = { onClick() },
        colors = ButtonDefaults.buttonColors(containerColor = getTypeColor(type))
    ) {
        Row {
            Image(
                modifier = Modifier.size(24.dp),
                painter = painterResource(id = resId),
                contentDescription = type
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = type.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() },
                style = TextStyle(fontSize = 14.sp, color = Color.White)
            )
        }
    }
}

fun getTypeDrawableId(type: String): Int {
    return when (type.lowercase()) {
        "grass" -> R.drawable.ic_grass_type
        "dragon" -> R.drawable.ic_dragon_type
        "fire" -> R.drawable.ic_fire_type
        "water" -> R.drawable.ic_water_type
        "electric" -> R.drawable.ic_electric_type
        "psychic" -> R.drawable.ic_psychic_type
        "ice" -> R.drawable.ic_ice_type
        "fighting" -> R.drawable.ic_fighter_type
        "poison" -> R.drawable.ic_poison_type
        "ground" -> R.drawable.ic_ground_type
        "flying" -> R.drawable.ic_fly_type
        "bug" -> R.drawable.ic_bug_type
        "rock" -> R.drawable.ic_rock_type
        "ghost" -> R.drawable.ic_ghost_type
        "steel" -> R.drawable.ic_steel_type
        "fairy" -> R.drawable.ic_fairy_type
        "dark" -> R.drawable.ic_dark_type
        "normal" -> R.drawable.ic_normal_type
        else -> R.drawable.ic_grass_type
    }
}

fun getTypeColor(type: String): Color {
    return when (type.lowercase()) {
        "grass" -> Color(0xFF4CAF50)
        "dragon" -> Color(0xFF673AB7)
        "fire" -> Color(0xFFFF5722)
        "water" -> Color(0xFF03A9F4)
        "electric" -> Color(0xFFFFEB3B)
        "psychic" -> Color(0xFFE91E63)
        "ice" -> Color(0xFF00BCD4)
        "fighting" -> Color(0xFFD32F2F)
        "poison" -> Color(0xFF9C27B0)
        "ground" -> Color(0xFF795548)
        "flying" -> Color(0xFF2196F3)
        "bug" -> Color(0xFFCDDC39)
        "rock" -> Color(0xFF607D8B)
        "ghost" -> Color(0xFF9E9E9E)
        "steel" -> Color(0xFF9E9E9E)
        "fairy" -> Color(0xFFFFC107)
        "dark" -> Color(0xFF212121)
        "normal" -> Color(0xFF9E9E9E)
        else -> Color(0xFF4CAF50)  // Un color por defecto para tipos desconocidos
    }
}

@Composable
fun InfoColumn(@DrawableRes icon: Int, label: String, value: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = icon),
            contentDescription = null,
            colorFilter = ColorFilter.tint(Color.Gray),
            modifier = Modifier.size(24.dp)
        )
        Text(
            text = label, style = TextStyle(
                fontSize = 14.sp, color = Color.Gray, fontWeight = FontWeight.Medium
            )
        )
        Text(
            text = value, style = TextStyle(
                fontSize = 14.sp, color = Color.Black, fontWeight = FontWeight.Bold
            )
        )
    }
}