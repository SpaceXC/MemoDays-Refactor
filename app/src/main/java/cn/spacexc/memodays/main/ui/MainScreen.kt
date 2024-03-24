package cn.spacexc.memodays.main.ui

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cn.spacexc.memodays.R
import cn.spacexc.memodays.add.ui.AddMemoActivity
import cn.spacexc.memodays.common.ui.BackgroundFrame
import cn.spacexc.memodays.common.ui.Card
import cn.spacexc.memodays.common.ui.IconText
import cn.spacexc.memodays.common.ui.MemoDayCard
import cn.spacexc.memodays.common.ui.clickVfx
import cn.spacexc.memodays.common.ui.miSans
import cn.spacexc.memodays.model.MemoDay

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Activity.MainScreen(
    viewModel: MainViewModel
) {
    val list by viewModel.memoDays.collectAsState(initial = emptyList())
    BackgroundFrame(onBack = null, title = stringResource(id = R.string.app_name)) {
        LazyColumn(contentPadding = PaddingValues(8.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            item {
                Card(modifier = Modifier.fillMaxWidth().clickVfx {
                    /*val day = MemoDay("test day", System.currentTimeMillis())
                    viewModel.addDay(day)*/
                    startActivity(Intent(this@MainScreen, AddMemoActivity::class.java))
                }.animateItemPlacement(), paddingValues = PaddingValues(vertical = 12.dp, horizontal = 10.dp), shape = RoundedCornerShape(10.dp)) {
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
            list.asReversed().forEach { memoDay ->
                item(key = memoDay.id.toHexString()) {
                    MemoDayCard(memoDay = memoDay, modifier = Modifier.animateItemPlacement())
                }
            }
        }
    }
}