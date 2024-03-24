package cn.spacexc.memodays.add.ui

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.tooling.preview.devices.WearDevice
import androidx.wear.tooling.preview.devices.WearDevices
import cn.spacexc.memodays.R
import cn.spacexc.memodays.common.ui.BackgroundFrame
import cn.spacexc.memodays.common.ui.Card
import cn.spacexc.memodays.common.ui.IconText
import cn.spacexc.memodays.common.ui.clickVfx
import cn.spacexc.memodays.common.ui.miSans
import cn.spacexc.memodays.main.ui.MainScreen
import cn.spacexc.memodays.main.ui.MainViewModel
import cn.spacexc.memodays.model.MemoDay

@Composable
fun Activity.AddMemoDayScreen(
    viewModel: MainViewModel,
    onBack: () -> Unit
) {
    var name by remember {
        mutableStateOf("")
    }
    BackgroundFrame(onBack = onBack, title = stringResource(id = R.string.add_memo_day)) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            TitledTextField(title = stringResource(id = R.string.title), value = name) {
                name = it
            }
            Card(modifier = Modifier.fillMaxWidth().clickVfx {
                val day = MemoDay(name, System.currentTimeMillis())
                viewModel.addDay(day)
                finish()
            }, paddingValues = PaddingValues(vertical = 12.dp, horizontal = 10.dp), shape = RoundedCornerShape(10.dp)) {
                IconText(
                    text = stringResource(id = R.string.add_memo_day),
                    fontFamily = miSans,
                    fontWeight = FontWeight.Medium,
                    fontSize = 12.sp,
                    color = Color.White,
                    modifier = Modifier.align(Alignment.Center)
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }
    }
}

@Composable
fun TitledField(
    modifier: Modifier = Modifier,
    title: String,
    content: @Composable BoxScope.() -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(4.dp), modifier = modifier) {
        Text(
            text = title,
            fontFamily = miSans,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            color = Color.White,
            modifier = Modifier
                .offset(x = 2.dp)
                .alpha(0.5f)
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(61, 61, 61, 255), RoundedCornerShape(10.dp))
        ) {
            content()
        }
    }
}

@Composable
fun TitledTextField(
    title: String,
    value: String,
    onValueChanged: (String) -> Unit
) {
    TitledField(title = title) {
        BasicTextField(
            value = value,
            onValueChange = onValueChanged,
            textStyle = TextStyle(
                color = Color.White,
                fontFamily = miSans,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp
            ),
            cursorBrush = SolidColor(Color.White),
            singleLine = true,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 12.dp)
        )
    }
}

@Composable
@Preview(device = WearDevices.RECT, showSystemUi = true)
@Preview(device = WearDevices.SMALL_ROUND, showSystemUi = true)
private fun TextFieldPreview() {
    var value by remember {
        mutableStateOf("")
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp)
    ) {
        TitledField(title = "字段") {
            BasicTextField(
                value = value,
                onValueChange = {
                    value = it
                },
                textStyle = TextStyle(
                    fontFamily = miSans,
                    fontWeight = FontWeight.Medium,
                    fontSize = 13.sp
                ),
                modifier = Modifier
                    .padding(horizontal = 8.dp, vertical = 8.dp)
                    .fillMaxWidth()
            )
        }
    }
}