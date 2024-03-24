package cn.spacexc.memodays.common.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun Card(
    modifier: Modifier,
    paddingValues: PaddingValues = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
    shape: Shape = RoundedCornerShape(8.dp),
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = modifier
            .background(
                color = Color(61, 61, 61, 255),
                shape = shape
            )
            .border(0.2.dp, color = Color(0xFF707070), shape = shape)
            .padding(paddingValues)
    ) {
        content()
    }
}

@Preview
@Composable
private fun CardPreview() {
    Card(modifier = Modifier.padding(20.dp)) {
        Text(text = "this is a test card", color = Color.White, fontFamily = miSans)
    }
}

@Preview
@Composable
private fun IconTextCardPreview() {
    Card(modifier = Modifier.padding(20.dp)) {
        IconText(text = "this is a test card", color = Color.White, fontFamily = miSans) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}