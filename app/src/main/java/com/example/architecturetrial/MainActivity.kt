package com.example.architecturetrial

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.HasDefaultViewModelProviderFactory
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel


import com.example.architecturetrial.ui.theme.ArchitectureTrialTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContent {
            ArchitectureTrialTheme {
                val intContent = GenericClass(120)
                val stringContent=GenericClass("Hello minasan")

                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.errorContainer){
                    Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center){
                        CounterView()
                        Spacer(modifier=Modifier.height(10.dp))
                        Text("Integer value:${intContent.content}  ")
                        Spacer(modifier = Modifier.height(10.dp))
                        Text("String:${stringContent.content}")
                        printContent(content = "Tehelka omlette")
                    }

                }


            }

        }

    }



}
@Composable
fun CounterView(counterVM: CounterViewModel = viewModel()){
    val counterState=counterVM.counter.value
    Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center){

        Text(text="Current Counter value: ${counterState.count}")
        Row{
            Button(onClick = {counterVM.increment()}){
                Text("Increment")
            }
            Spacer(modifier = Modifier.width(10.dp))
            Button(onClick = {counterVM.decrement()}){
                Text("Decrement")
            }
        }
        Button(onClick = {counterVM.resetValue()}){
            Text("Reset")
        }
    }
}
@Composable
fun<T> printContent(content: T){
    Text("Aaj omlette nahi ${content} banaunga")
}
data class Counter(val count:Int)
data class User(val username:String, val password:String)

class CounterViewModel: ViewModel(){
    private val _counter= mutableStateOf(Counter(0))
    val counter: State<Counter> = _counter
    fun increment(){
        _counter.value = Counter(_counter.value.count+1)
    }
    fun decrement(){
        _counter.value=Counter(_counter.value.count -1)
    }
    fun resetValue(){
        _counter.value=Counter(0)
    }
}

class GenericClass<T>(var content: T)
