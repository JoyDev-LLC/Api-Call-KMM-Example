package com.example.appcocoapods.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.appcocoapods.DuckViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    DuckView(viewModel = DuckViewModel())
                }
            }
        }
    }
}

@Composable
fun DuckView(viewModel: DuckViewModel) {

    val duckUrl = viewModel.duckUrl.collectAsState()

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(duckUrl.value)
                .crossfade(false)
                .build(),
            contentDescription = null
        )
        Spacer(modifier = Modifier.padding(vertical = 10.dp))
        Button(
            onClick = viewModel::updateDuckAndroid
        ) {
            Text(text = "Get new duck!")
        }
    }

}

@Preview
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        DuckView(viewModel = DuckViewModel())
    }
}
