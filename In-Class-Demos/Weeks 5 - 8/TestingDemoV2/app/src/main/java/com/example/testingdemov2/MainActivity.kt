package com.example.testingdemov2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.testingdemov2.ui.theme.TestingDemoV2Theme
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.milliseconds

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TestingDemoV2Theme {
                Column(Modifier.statusBarsPadding()) {
                    Counter()
                    DisappearingSection()
                }
            }
        }
    }
}

@Composable
fun Counter() {
    var count by remember { mutableStateOf(0) }
    Column(
        modifier = Modifier.fillMaxWidth().padding(16.dp),
        horizontalAlignment =Alignment.CenterHorizontally
    ) {
        Text("Count: $count",
            Modifier.testTag("counterDisplay"),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold)

        Spacer(Modifier.height(16.dp))
        OutlinedTextField(
            value = "Count: $count",
            onValueChange = {}
        )

        Spacer(Modifier.height(16.dp))
        Button(onClick = { count++ }) {
            Text("Add Count")
        }
    }
}


@Composable
fun DisappearingSection() {
    var showTop by remember { mutableStateOf(true) }
    //one LaunchedEffect waits, changes showTop, then finishes.
    // Because the key changed, Compose starts a new effect for the next two-second wait.
    //    LaunchedEffect(key1 = showTop) {
    //        delay(2000.milliseconds)
    //        showTop = !showTop
    //    }

     //one LaunchedEffect(Unit) starts once and remains active.
    // Its while loop performs every future wait and toggle itself.
    LaunchedEffect(Unit) {
            while (true) {
                delay(2000.milliseconds)
                showTop = !showTop
        }
    }

    AnimatedVisibility(visible = showTop) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(Color.LightGray),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Testing Demo", fontSize = 18.sp)
        }
    }

}