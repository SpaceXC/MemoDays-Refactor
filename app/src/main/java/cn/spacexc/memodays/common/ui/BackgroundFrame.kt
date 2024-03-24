package cn.spacexc.memodays.common.ui

import android.os.Build
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBackIos
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.PlaceholderVerticalAlign
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.tooling.preview.devices.WearDevices

@Composable
fun isRound() =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) LocalConfiguration.current.isScreenRound else false

@Composable
fun BackgroundFrame(
    onBack: (() -> Unit)?,
    title: String,
    content: @Composable () -> Unit
) {
    val timeSource = DefaultTimeSource("HH:mm")
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(if (isRound()) 4.dp else 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Column {
                if(isRound()) {
                    Text(
                        text = timeSource.currentTime,
                        fontFamily = miSans,
                        fontWeight = FontWeight.Medium,
                        fontSize = 10.sp,
                        color = Color.White,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                }
                Text(
                    text = buildAnnotatedString {
                        onBack?.let {
                            appendInlineContent(id = "backIcon")
                        }
                        append(title)
                    },
                    inlineContent = mapOf(
                        "backIcon" to InlineTextContent(
                            placeholder = Placeholder(
                                width = 13.sp,
                                height = 13.sp,
                                placeholderVerticalAlign = PlaceholderVerticalAlign.Center
                            )
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Default.ArrowBackIos,
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.fillMaxSize()
                            )
                        }),
                    textAlign = if (isRound()) TextAlign.Center else null,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontFamily = miSans,
                    fontWeight = FontWeight.Medium,
                    fontSize = 13.sp,
                    color = Color.White,
                    modifier = Modifier.clickable {
                        onBack?.invoke()
                    }
                )
            }
            if (!isRound()) {
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = timeSource.currentTime,
                    fontFamily = miSans,
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp,
                    color = Color.White
                )
            }
        }
        Box(modifier = Modifier.weight(1f)) {
            content()
        }
    }
}

@Preview(device = WearDevices.RECT, showSystemUi = true)
@Preview(device = WearDevices.SMALL_ROUND, showSystemUi = true)
@Composable
private fun BackgroundFramePreview() {
    BackgroundFrame(onBack = null, title = "Preview") {
        Text(text = "sdsds", color = Color.White)
    }
}
@Preview(device = WearDevices.RECT, showSystemUi = true)
@Preview(device = WearDevices.SMALL_ROUND, showSystemUi = true)
@Composable
private fun BackgroundFramePreviewWithArrow() {
    BackgroundFrame(onBack = {  }, title = "Preview") {
        Text(text = "sdsds", color = Color.White)
    }
}