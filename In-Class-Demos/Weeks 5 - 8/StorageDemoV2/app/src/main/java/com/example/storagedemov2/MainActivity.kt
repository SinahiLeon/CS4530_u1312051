package com.example.storagedemov2

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.os.Environment
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.storagedemov2.ui.theme.StorageDemoV2Theme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StorageDemoV2Theme {
                PersistentScreen(this)
            }
        }
    }
}
val Context.datastore by preferencesDataStore("settings")
val THEME_KEY = booleanPreferencesKey("dark_theme")
val COUNTER_KEY = intPreferencesKey("0")

suspend fun saveTheme (context: Context, isDark: Boolean){
    context.datastore.edit {
        it[THEME_KEY] = isDark
    }
}

suspend fun writeToFile(context: Context, filename: String, content: String)= withContext(Dispatchers.IO){
    context.openFileOutput(filename, Context.MODE_PRIVATE).use{
        it.write(content.toByteArray())
    }
}

// No need to use return here because withContext { ... }
// already returns the value of its last expression.
suspend fun readFromFile (context: Context, filename:String):String = withContext(Dispatchers.IO){
    // we're using .use so the reader is always closed
    context.openFileInput(filename).bufferedReader().use{it.readText()}
}

suspend fun writeExternalFile (context:Context, content:String)= withContext(Dispatchers.IO){
    val file = File (context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS),"notesHome.txt")
    file.writeText(content)
}



//suppressing lines 91-93 is acceptable for this demo.
@SuppressLint("FlowOperatorInvokedInComposition")
@Composable
fun PersistentScreen(context: Context) {
    var input by remember { mutableStateOf("") }
    var status by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }

    val theme by context.datastore.data.map {
        it[THEME_KEY] ?: false
    }.collectAsState(initial = false)

    //A cleaner Compose-friendly version is
    // to remember the Flow
    //val themeFlow = remember(context) {
    //    context.dataStore.data.map { preferences ->
    //        preferences[THEME_KEY] ?: false
    //    }
    //}
    //val theme by themeFlow.collectAsState(initial = false)
    val coroutineScope = rememberCoroutineScope()

    Column(Modifier.fillMaxSize().padding(20.dp)) {
        Spacer(Modifier.height(40.dp))
        Text("DataStore Preferences", fontSize = 20.sp, fontWeight = FontWeight.Bold, color=Color.Blue)
        Text("Dark theme: ")
        Button(onClick = {
            coroutineScope.launch {
                saveTheme(context, !theme)
            }
        }) {
            Text("Change Theme: $theme")
        }

        Spacer(Modifier.height(10.dp))

        Text("Counter: ")
        Button(onClick = {
            coroutineScope.launch {

            }
        }) {
            Text("Increment")
        }

        Spacer(Modifier.height(20.dp))

        Text("Files", fontSize = 20.sp, fontWeight = FontWeight.Bold, color=Color.Blue)
        OutlinedTextField(
            value = input,
            onValueChange = { input = it },
            label = { Text("Content") }
        )

        Spacer(Modifier.height(10.dp))

        Button(onClick = {
            coroutineScope.launch {
                writeToFile(context, "file1.txt", input)
                input = ""
            }
        }) {
            Text("Save File (Internal Storage)")
        }

        Spacer(Modifier.height(20.dp))

        Button(onClick = {
            coroutineScope.launch {
                content = readFromFile(context,"file1.txt")
            }
        }) {
            Text("Read File (Internal Storage)")
        }
        Text("Content: $content", fontWeight = FontWeight.Bold)

        Spacer(Modifier.height(40.dp))

        Button(onClick = {
            coroutineScope.launch {
                input = ""
                status = "Cache File Written"
            }
        }) {
            Text("Save to Cache")
        }

        Button(onClick = {
            coroutineScope.launch {
                writeExternalFile(context, input)
                input = ""
                status = "Written to app-specific external folder"
            }
        }) {
            Text("Save to app-specific external folder")
        }
        Text(status)
    }

}