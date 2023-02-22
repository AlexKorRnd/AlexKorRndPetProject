package ru.alexkorrnd.designsystem.core.shimmers

import android.content.res.Configuration
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.alexkorrnd.designsystem.core.theme.AlexKorRndPetProjectTheme

val ShimmerColorShades = listOf(
    Color.LightGray.copy(0.9f),
    Color.LightGray.copy(0.2f),
    Color.LightGray.copy(0.9f)
)

private const val DURATION_MILLIS = 500

@Composable
fun ShimmerAnimation(
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(4.dp),
    durationMillis: Int = DURATION_MILLIS,
) {

    val transition = rememberInfiniteTransition()
    val translateAnim by transition.animateFloat(
        initialValue = 0f,
        targetValue = 500f,
        animationSpec = infiniteRepeatable(
            tween(durationMillis = durationMillis, easing = FastOutSlowInEasing),
            RepeatMode.Reverse
        )
    )

    val brush = Brush.linearGradient(
        colors = ShimmerColorShades,
        start = Offset(10f, 10f),
        end = Offset(translateAnim, translateAnim)
    )
    Box(
        modifier = modifier
            .background(brush, shape)
    )
}

@RequiresApi(Build.VERSION_CODES.FROYO)
@Preview(
    name = "day",
    showBackground = true,
    device = Devices.PIXEL_4_XL,
    backgroundColor = 0xFFFFFF,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
fun ShimmerPreview() {
    AlexKorRndPetProjectTheme {
        ShimmerAnimation(
            Modifier
                .size(60.dp)
                .clip(RoundedCornerShape(5.dp))
        )
    }
}