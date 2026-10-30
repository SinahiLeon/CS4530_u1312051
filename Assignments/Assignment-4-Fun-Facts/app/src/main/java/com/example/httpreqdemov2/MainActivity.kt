package com.example.httpreqdemov2

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.httpreqdemov2.ui.theme.HTTPReqDemoV2Theme
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
data class FunFact(var text: String)

object ApiClient {
    val client = HttpClient(Android) {
        install(ContentNegotiation) {
            json(Json {
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
    }
}

@OptIn(ExperimentalCoroutinesApi::class)
class MyViewModel(
    private val repository: FactRepository
) : ViewModel() {

    val searchText = MutableStateFlow("")

    val factsListReadOnly: StateFlow<List<SavedFact>> =
        searchText.flatMapLatest { keyword ->
            repository.searchFacts(keyword)
        }.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            emptyList()
        )

    suspend fun fetchItem() {
        try {
            repository.fetchFact()
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            Log.e("FunFact", "Error Fetching", e)
        }
    }
}

class FactViewModelFactory(
    private val repository: FactRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MyViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MyViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel")
    }
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HTTPReqDemoV2Theme {
                val app = application as FunFactsApplication
                val vm: MyViewModel = viewModel(
                    factory = FactViewModelFactory(app.repository)
                )
                FactsJPC(vm)
            }
        }
    }
}

@Composable
fun FactsJPC(myVM: MyViewModel) {
    Column(
        Modifier.fillMaxWidth().padding(50.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        val scope = rememberCoroutineScope()
        val observableFacts by myVM.factsListReadOnly.collectAsState()
        val itemText by myVM.searchText.collectAsState()

        OutlinedTextField(
            value = itemText,
            onValueChange = { myVM.searchText.value = it },
            label = { Text("Search saved facts") },
            modifier = Modifier.fillMaxWidth()
        )

        Row {
            Button(onClick = {
                scope.launch {
                    myVM.fetchItem()
                }
            }) {
                Text("Fetch Item")
            }
        }

        Spacer(Modifier.height(20.dp))
        Text(
            "FunFact List",
            fontSize = 25.sp,
            fontWeight = FontWeight.ExtraBold,
            color = Color.Blue
        )
        Row {
            LazyColumn {
                items(observableFacts) {
                    Text(
                        it.text,
                        fontSize = 20.sp,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(Modifier.height(10.dp))
                }
            }
        }
    }
}