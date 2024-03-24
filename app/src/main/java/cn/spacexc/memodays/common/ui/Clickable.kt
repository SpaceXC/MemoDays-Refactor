package cn.spacexc.memodays.common.ui

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.scale

@Composable
fun rememberMutableInteractionSource() = remember {
    MutableInteractionSource()
}

@Composable
fun Modifier.clickVfx(
    interactionSource: MutableInteractionSource = rememberMutableInteractionSource(),
    isEnabled: Boolean = true,
    onClick: () -> Unit,
): Modifier = composed {
    if (isEnabled) {
        val isPressed by interactionSource.collectIsPressedAsState()
        val sizePercent by animateFloatAsState(
            targetValue = if (isPressed) 0.9f else 1f,
            animationSpec = tween(durationMillis = 150), label = ""
        )
        scale(sizePercent).clickable(
            indication = null, interactionSource = interactionSource, onClick = onClick
        )
    } else {
        Modifier
    }
}