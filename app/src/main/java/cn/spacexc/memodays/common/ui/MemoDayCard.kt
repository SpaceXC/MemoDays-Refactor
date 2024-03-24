package cn.spacexc.memodays.common.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.tooling.preview.devices.WearDevices
import cn.spacexc.memodays.common.minusTimeToDate
import cn.spacexc.memodays.common.toDateString
import cn.spacexc.memodays.model.MemoDay
import java.util.Date

@Composable
fun MemoDayCard(
    modifier: Modifier = Modifier,
    memoDay: MemoDay
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.Black)
            .clip(RoundedCornerShape(8.dp))
            .border(width = 0.1.dp, color = Color(61, 61, 61, 255), RoundedCornerShape(8.dp))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(android.graphics.Color.parseColor(memoDay.color)))
                .padding(start = 14.dp, end = 14.dp, top = 14.dp, bottom = 10.dp),
            horizontalAlignment = if (isRound()) Alignment.CenterHorizontally else Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            Text(
                text = "距离",
                color = Color.White,
                modifier = Modifier.alpha(0.67f),
                fontSize = 11.sp,
                fontFamily = miSans
            )
            Text(
                text = memoDay.name,
                color = Color.White,
                fontSize = 16.sp,
                fontFamily = miSans,
                fontWeight = FontWeight.Medium
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 14.dp, end = 14.dp, top = 10.dp, bottom = 14.dp),
            horizontalAlignment = if (isRound()) Alignment.CenterHorizontally else Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            val currentTime = System.currentTimeMillis()
            if (currentTime > memoDay.targetTime) {
                val difference = currentTime.minusTimeToDate(memoDay.targetTime)
                if (difference == 0) {
                    Text(
                        text = "今天",
                        color = Color.White,
                        fontFamily = miSans,
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp
                    )
                } else {
                    Text(
                        text = "已经过去",
                        fontFamily = miSans,
                        fontSize = 12.sp,
                        color = Color.White,
                        modifier = Modifier.alpha(0.6f)
                    )
                    Row(verticalAlignment = Alignment.Bottom) {
                        Text(
                            text = "$difference",
                            color = Color.White,
                            fontFamily = miSans,
                            fontWeight = FontWeight.Bold,
                            fontSize = 24.sp
                        )
                        Spacer(modifier = Modifier.width(2.dp))
                        Text(
                            text = "天",
                            color = Color.White,
                            fontFamily = miSans,
                            fontWeight = FontWeight.Medium,
                            fontSize = 12.sp,
                            modifier = Modifier.offset(y = (-4).dp)
                        )
                    }
                }
                Text(
                    text = memoDay.targetTime.toDateString(),
                    fontFamily = miSans,
                    fontSize = 12.sp,
                    color = Color.White,
                    modifier = Modifier.alpha(0.6f),
                    fontWeight = FontWeight.Medium
                )

            }
            else {
                val difference = memoDay.targetTime.minusTimeToDate(currentTime)
                if (difference == 0) {
                    Text(
                        text = "今天",
                        color = Color.White,
                        fontFamily = miSans,
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp
                    )
                } else {
                    Text(
                        text = "还有",
                        fontFamily = miSans,
                        fontSize = 12.sp,
                        color = Color.White,
                        modifier = Modifier.alpha(0.6f)
                    )
                    Row(verticalAlignment = Alignment.Bottom) {
                        Text(
                            text = "$difference",
                            color = Color.White,
                            fontFamily = miSans,
                            fontWeight = FontWeight.Bold,
                            fontSize = 24.sp
                        )
                        Spacer(modifier = Modifier.width(2.dp))
                        Text(
                            text = "天",
                            color = Color.White,
                            fontFamily = miSans,
                            fontWeight = FontWeight.Medium,
                            fontSize = 12.sp,
                            modifier = Modifier.offset(y = (-4).dp)
                        )
                    }
                }
                Text(
                    text = memoDay.targetTime.toDateString(),
                    fontFamily = miSans,
                    fontSize = 12.sp,
                    color = Color.White,
                    modifier = Modifier.alpha(0.6f),
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}

@Preview(device = WearDevices.RECT, showSystemUi = true)
@Preview(device = WearDevices.SMALL_ROUND, showSystemUi = true)
@Composable
private fun MemoDayCardCountUpPreview() {
    val day = MemoDay(
        name = "开学",
        targetTime = Date(2024 - 1900, 2 - 1, 26).time,
    )
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp), contentAlignment = Alignment.Center
    ) {
        MemoDayCard(memoDay = day)
    }
}

@Preview(device = WearDevices.RECT, showSystemUi = true)
@Preview(device = WearDevices.SMALL_ROUND, showSystemUi = true)
@Composable
private fun MemoDayCardCountDownPreview() {
    val day = MemoDay(
        name = "生日",
        targetTime = Date(2024 - 1900, 2 - 1, 6).time,
    )
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp), contentAlignment = Alignment.Center
    ) {
        MemoDayCard(memoDay = day)
    }
}

@Preview(device = WearDevices.RECT, showSystemUi = true)
@Preview(device = WearDevices.SMALL_ROUND, showSystemUi = true)
@Composable
private fun MemoDayCardPreview() {
    val day = MemoDay(
        name = "2024年2月23日",
        targetTime = Date(2024 - 1900, 2 - 1, 23).time,
    )
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp), contentAlignment = Alignment.Center
    ) {
        MemoDayCard(memoDay = day)
    }
}