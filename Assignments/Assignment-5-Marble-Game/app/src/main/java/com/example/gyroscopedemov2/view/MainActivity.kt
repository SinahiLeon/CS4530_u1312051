package com.example.gyroscopedemov2.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.gyroscopedemov2.model.MarbleRepository
import com.example.gyroscopedemov2.ui.theme.GyroscopeDemoV2Theme
import com.example.gyroscopedemov2.viewmodel.MarbleViewModel
import kotlin.getValue

class MainActivity : ComponentActivity() {

    val marbleviewModel: MarbleViewModel by viewModels()
    lateinit var repository: MarbleRepository

    var maxX = 0f
    var maxY = 0f
    var lastTime = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        repository = MarbleRepository(applicationContext)

        setContent {
            GyroscopeDemoV2Theme {
                MarbleScreen(marbleviewModel) { width, height ->
                    maxX = width
                    maxY = height
                }
            }
        }

    }


    // Register when activity resumes
    override fun onResume() {
        super.onResume()
        lastTime = 0L

        repository.startListening { reading ->
            val now = System.nanoTime()
            val dt = if (lastTime == 0L) {
                0f
            } else ((now - lastTime) / 1_000_000_000f)
                .coerceAtMost(0.05f)

            lastTime = now
            marbleviewModel.updatePosition(
                reading.x, reading.y, dt, maxX, maxY
            )

        }

    }

    // Unregister when activity pauses
    override fun onPause() {
        repository.stopListening()
        super.onPause()
    }
}

@Composable
fun MarbleScreen(
    viewModel: MarbleViewModel,
    onSizeChanged: (Float, Float) -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxSize().statusBarsPadding(),
        color = MaterialTheme.colorScheme.background
    ) {
        BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
            val marbleSize = 40.dp
            val maxX = maxWidth.value - marbleSize.value
            val maxY = maxHeight.value - marbleSize.value

            onSizeChanged(maxX.coerceAtLeast(0f), maxY.coerceAtLeast(0f))

            Box(
                modifier = Modifier
                    .offset(viewModel.x.dp, viewModel.y.dp)
                    .size(marbleSize)
                    .background(Color.Blue, CircleShape)
            )
        }
    }
}
