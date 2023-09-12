package exirium.pe.pokedex.core.ui

import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import exirium.pe.pokedex.R
import java.util.Locale
import kotlin.math.roundToInt

@Composable
fun PokemonTypeAnimation(typeState: MutableState<String>) {
    var currentType by remember { mutableStateOf(typeState.value) }

    LaunchedEffect(typeState.value) {
        currentType = typeState.value
    }

    when (currentType.lowercase(Locale.ROOT)) {
        "normal" -> NormalTypeAnimation()
        "fire" -> FireTypeAnimation()
        "water" -> WaterTypeAnimation()
        "electric" -> ElectricTypeAnimation()
        "grass" -> GrassTypeAnimation()
        "ice" -> IceTypeAnimation()
        "fighting" -> FightingTypeAnimation()
        "poison" -> PoisonTypeAnimation()
        "ground" -> GroundTypeAnimation()
        "flying" -> FlyingTypeAnimation()
        "psychic" -> PsychicTypeAnimation()
        "bug" -> BugTypeAnimation()
        "rock" -> RockTypeAnimation()
        "ghost" -> GhostTypeAnimation()
        "dragon" -> DragonTypeAnimation()
        "dark" -> DarkTypeAnimation()
        "steel" -> SteelTypeAnimation()
        "fairy" -> FairyTypeAnimation()
    }
}

@Composable
private fun NormalTypeAnimation() {
    val infiniteTransition = rememberInfiniteTransition(label = "")
    val scale by infiniteTransition.animateFloat(
        initialValue = 1f, targetValue = 1.2f, animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000, easing = FastOutSlowInEasing
            ), repeatMode = RepeatMode.Reverse
        ), label = ""
    )

    Image(
        painter = painterResource(id = R.drawable.ic_normal_type),
        colorFilter = ColorFilter.tint(Color.White),
        contentDescription = "Normal Type Icon",
        modifier = Modifier
            .size(200.dp)
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
            .scale(scale)
    )
}

@Composable
private fun PsychicTypeAnimation() {
    val infiniteTransition = rememberInfiniteTransition(label = "")

    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f, targetValue = 360f, animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 5000, easing = LinearEasing
            ), repeatMode = RepeatMode.Restart
        ), label = ""
    )

    Image(
        painter = painterResource(id = R.drawable.ic_psychic_type),
        colorFilter = ColorFilter.tint(Color.White),
        contentDescription = "Psychic Type Icon",
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
            .size(200.dp)
            .rotate(rotation)
    )
}

@Composable
private fun ElectricTypeAnimation() {
    val infiniteTransition = rememberInfiniteTransition(label = "")

    val alpha by infiniteTransition.animateFloat(
        initialValue = 1f, targetValue = 0.5f, animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 300, easing = LinearEasing
            ), repeatMode = RepeatMode.Reverse
        ), label = ""
    )

    Image(
        painter = painterResource(id = R.drawable.ic_electric_type),
        colorFilter = ColorFilter.tint(Color.White),
        contentDescription = "Electric Type Icon",
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
            .size(200.dp)
            .graphicsLayer(alpha = alpha)
    )
}

@Composable
private fun FlyingTypeAnimation() {
    val infiniteTransition = rememberInfiniteTransition(label = "")

    val offsetY by infiniteTransition.animateFloat(
        initialValue = 0f, targetValue = 30f, animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000, easing = LinearEasing
            ), repeatMode = RepeatMode.Reverse
        ), label = ""
    )

    Image(
        painter = painterResource(id = R.drawable.ic_fly_type),
        colorFilter = ColorFilter.tint(Color.White),
        contentDescription = "Flying Type Icon",
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
            .size(200.dp)
            .offset(y = offsetY.dp)
    )
}

@Composable
private fun GrassTypeAnimation() {
    val infiniteTransition = rememberInfiniteTransition(label = "")

    val scale by infiniteTransition.animateFloat(
        initialValue = 1f, targetValue = 1.2f, animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 2000, easing = LinearEasing
            ), repeatMode = RepeatMode.Reverse
        ), label = ""
    )

    Image(
        painter = painterResource(id = R.drawable.ic_grass_type),
        contentDescription = "Grass Type Icon",
        colorFilter = ColorFilter.tint(Color.White),
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 20.dp)
            .wrapContentSize(Alignment.Center)
            .size(200.dp)
            .scale(scale)
    )
}

@Composable
private fun FireTypeAnimation() {
    val infiniteTransition = rememberInfiniteTransition(label = "")

    val alpha by infiniteTransition.animateFloat(
        initialValue = 0.5f, targetValue = 1f, animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 500, easing = FastOutSlowInEasing
            ), repeatMode = RepeatMode.Reverse
        ), label = ""
    )

    Image(
        painter = painterResource(id = R.drawable.ic_fire_type),
        contentDescription = "Fire Type Icon",
        colorFilter = ColorFilter.tint(Color.White),
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
            .padding(top = 20.dp)
            .size(200.dp)
            .graphicsLayer(alpha = alpha)
    )
}

@Composable
private fun WaterTypeAnimation() {
    val infiniteTransition = rememberInfiniteTransition(label = "")

    val translateY by infiniteTransition.animateFloat(
        initialValue = 0f, targetValue = 50f, animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000, easing = LinearEasing
            ), repeatMode = RepeatMode.Reverse
        ), label = ""
    )

    Image(
        painter = painterResource(id = R.drawable.ic_water_type),
        contentDescription = "Water Type Icon",
        colorFilter = ColorFilter.tint(Color.White),
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
            .padding(top = 20.dp)
            .size(200.dp)
            .graphicsLayer(
                translationY = translateY
            )
    )
}

@Composable
private fun BugTypeAnimation() {
    val infiniteTransition = rememberInfiniteTransition(label = "")

    val deviationX by infiniteTransition.animateFloat(
        initialValue = - 10f, targetValue = 10f, animationSpec = infiniteRepeatable(
            animation = tween(800, easing = FastOutSlowInEasing), repeatMode = RepeatMode.Reverse
        ), label = ""
    )

    val deviationY by infiniteTransition.animateFloat(
        initialValue = - 10f, targetValue = 10f, animationSpec = infiniteRepeatable(
            animation = tween(700, easing = FastOutSlowInEasing), repeatMode = RepeatMode.Reverse
        ), label = ""
    )

    Box(
        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_bug_type),
            colorFilter = ColorFilter.tint(Color.White),
            contentDescription = "Bug Type Icon",
            modifier = Modifier
                .size(200.dp)
                .padding(top = 20.dp)
                .offset(x = deviationX.dp, y = deviationY.dp)
        )
    }
}

@Composable
private fun FairyTypeAnimation() {
    val infiniteTransition = rememberInfiniteTransition(label = "")

    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f, targetValue = 360f, animationSpec = infiniteRepeatable(
            animation = tween(5000, easing = LinearEasing), repeatMode = RepeatMode.Restart
        ), label = ""
    )

    Box(
        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_fairy_type),
            colorFilter = ColorFilter.tint(Color.White),
            contentDescription = "Fairy Type Icon",
            modifier = Modifier
                .size(200.dp)
                .padding(top = 20.dp)
                .graphicsLayer(rotationZ = rotation)
        )
    }
}

@Composable
private fun GroundTypeAnimation() {
    val infiniteTransition = rememberInfiniteTransition(label = "")

    val offsetX by infiniteTransition.animateFloat(
        initialValue = - 5f, targetValue = 5f, animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 200, easing = FastOutLinearInEasing
            ), repeatMode = RepeatMode.Reverse
        ), label = ""
    )

    val offsetY by infiniteTransition.animateFloat(
        initialValue = - 5f, targetValue = 5f, animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 150, easing = FastOutLinearInEasing
            ), repeatMode = RepeatMode.Reverse
        ), label = ""
    )

    Box(
        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
    ) {
        Image(painter = painterResource(id = R.drawable.ic_ground_type),
            colorFilter = ColorFilter.tint(Color.White),
            contentDescription = "Ground Type Icon",
            modifier = Modifier
                .size(200.dp)
                .padding(top = 20.dp)
                .offset { IntOffset(offsetX.roundToInt(), offsetY.roundToInt()) })
    }
}

@Composable
private fun RockTypeAnimation() {
    val infiniteTransition = rememberInfiniteTransition(label = "")
    val offsetX by infiniteTransition.animateFloat(
        initialValue = - 10f, targetValue = 10f, animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 100, easing = LinearEasing
            ), repeatMode = RepeatMode.Reverse
        ), label = ""
    )

    Box(
        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_rock_type),
            colorFilter = ColorFilter.tint(Color.White),
            contentDescription = "Rock Type Icon",
            modifier = Modifier
                .size(200.dp)
                .padding(top = 20.dp)
                .offset(x = offsetX.dp)
        )
    }
}

@Composable
private fun PoisonTypeAnimation() {
    val infiniteTransition = rememberInfiniteTransition(label = "")

    val offsetX by infiniteTransition.animateFloat(
        initialValue = - 50f, targetValue = 50f, animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 2000, easing = LinearEasing
            ), repeatMode = RepeatMode.Reverse
        ), label = ""
    )

    Box(
        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
    ) {
        Image(painter = painterResource(id = R.drawable.ic_poison_type),
            colorFilter = ColorFilter.tint(Color.White),
            contentDescription = "Poison Type Icon",
            modifier = Modifier
                .size(200.dp)
                .padding(top = 20.dp)
                .offset { IntOffset(offsetX.roundToInt(), 0) })
    }
}

@Composable
private fun SteelTypeAnimation() {
    val infiniteTransition = rememberInfiniteTransition(label = "")

    val scale by infiniteTransition.animateFloat(
        initialValue = 1f, targetValue = 1.2f, animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000, easing = LinearEasing
            ), repeatMode = RepeatMode.Reverse
        ), label = ""
    )

    Box(
        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_steel_type),
            colorFilter = ColorFilter.tint(Color.White),
            contentDescription = "Steel Type Icon",
            modifier = Modifier
                .size(200.dp)
                .padding(top = 20.dp)
                .graphicsLayer(
                    scaleX = scale, scaleY = scale
                )
        )
    }
}

@Composable
private fun DragonTypeAnimation() {
    val infiniteTransition = rememberInfiniteTransition(label = "")

    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f, targetValue = 360f, animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 5000, easing = LinearEasing
            ), repeatMode = RepeatMode.Restart
        ), label = ""
    )

    Image(
        painter = painterResource(id = R.drawable.ic_dragon_type),
        colorFilter = ColorFilter.tint(Color.White),
        contentDescription = "Dragon Type Icon",
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
            .padding(top = 20.dp)
            .size(200.dp)
            .graphicsLayer(rotationZ = rotation)
    )
}

@Composable
private fun FightingTypeAnimation() {
    val infiniteTransition = rememberInfiniteTransition(label = "")

    val offsetX by infiniteTransition.animateFloat(
        initialValue = 0f, targetValue = 50f, animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 500, easing = LinearEasing
            ), repeatMode = RepeatMode.Reverse
        ), label = ""
    )

    Image(
        painter = painterResource(id = R.drawable.ic_fighter_type),
        colorFilter = ColorFilter.tint(Color.White),
        contentDescription = "Fighting Type Icon",
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 20.dp)
            .size(200.dp)
            .wrapContentSize(Alignment.Center)
            .offset(x = offsetX.dp)
    )
}

@Composable
private fun DarkTypeAnimation() {
    val infiniteTransition = rememberInfiniteTransition(label = "")

    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f, targetValue = 360f, animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 2000, easing = LinearEasing
            ), repeatMode = RepeatMode.Restart
        ), label = ""
    )

    val colorValue by infiniteTransition.animateFloat(
        initialValue = 0.2f, targetValue = 1f, animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 2000, easing = LinearEasing
            ), repeatMode = RepeatMode.Reverse
        ), label = ""
    )

    val darkColor = Color.White.copy(alpha = colorValue)

    Image(
        painter = painterResource(id = R.drawable.ic_dark_type),
        contentDescription = "Dark Type Icon",
        colorFilter = ColorFilter.tint(darkColor),
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
            .padding(top = 20.dp)
            .size(200.dp)
            .graphicsLayer(rotationZ = rotation)
    )
}

@Composable
private fun GhostTypeAnimation() {
    val infiniteTransition = rememberInfiniteTransition(label = "")

    val alpha by infiniteTransition.animateFloat(
        initialValue = 1f, targetValue = 0f, animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 2000, easing = LinearEasing
            ), repeatMode = RepeatMode.Reverse
        ), label = ""
    )

    Image(
        painter = painterResource(id = R.drawable.ic_ghost_type),
        colorFilter = ColorFilter.tint(Color.White),
        contentDescription = "Ghost Type Icon",
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
            .padding(top = 20.dp)
            .size(200.dp)
            .alpha(alpha)
    )
}

@Composable
private fun IceTypeAnimation() {
    val infiniteTransition = rememberInfiniteTransition(label = "")

    val alpha by infiniteTransition.animateFloat(
        initialValue = 1f, targetValue = 0.3f, animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 2000, easing = LinearEasing
            ), repeatMode = RepeatMode.Reverse
        ), label = ""
    )

    Image(
        painter = painterResource(id = R.drawable.ic_ice_type),
        colorFilter = ColorFilter.tint(Color.White),
        contentDescription = "Ice Type Icon",
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
            .padding(top = 20.dp)
            .size(200.dp)
            .graphicsLayer(alpha = alpha)
    )
}
