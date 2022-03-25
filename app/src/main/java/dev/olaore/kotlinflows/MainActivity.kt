package dev.olaore.kotlinflows

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.olaore.kotlinflows.ui.theme.KotlinFlowsTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KotlinFlowsTheme {
                val viewModel = viewModel<MainViewModel>()
                val number = viewModel.countdown.collectAsState(10)
                Surface(color = MaterialTheme.colors.background) {
                    MainActivityScreen(currentValue = number.value)
                }
            }
        }
    }
}

@Composable
fun MainActivityScreen(currentValue: Int, modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Text(
            currentValue.toString(),
            color = colorResource(R.color.black),
            fontSize = 50.sp,
            modifier = modifier.align(Alignment.Center),
            style = TextStyle(
                fontWeight = FontWeight.Bold
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    KotlinFlowsTheme {
        MainActivityScreen(currentValue = 1)
    }
}