package com.example.inclasspractice4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.inclasspractice4.ui.theme.InClassPractice4Theme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            InClassPractice4Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

// ORIGINAL GREETING FUNCTION
//@Composable
//fun Greeting(name: String, modifier: Modifier = Modifier) {
//    Text(
//        text = "Hello $name!",
//        modifier = modifier
//    )
//}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    var firstText by remember { mutableStateOf("") }
    var secondText by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }

    Column(
        modifier = modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(text = "Hello $name!")

        OutlinedTextField(
            value = firstText,
            onValueChange = { firstText = it },
            label = { Text("First text") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = secondText,
            onValueChange = { secondText = it },
            label = { Text("Second text") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                result = firstText + secondText
            }
        ) {
            Text("Submit")
        }

        Text(text = "Result: $result")
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    InClassPractice4Theme {
        Greeting("Android")
    }
}