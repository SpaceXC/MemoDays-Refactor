package cn.spacexc.memodays.add.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import cn.spacexc.memodays.main.ui.MainViewModel

class AddMemoActivity : ComponentActivity() {
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AddMemoDayScreen(viewModel = viewModel, onBack = ::finish)
        }
    }
}