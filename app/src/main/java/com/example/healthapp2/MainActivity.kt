package com.example.healthapp2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.healthapp2.ui.theme.HealthApp2Theme
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HealthApp2Theme {
                //Greeting( "Android")
                // This is wierd as calling the function here actually doesn't do
                // anything at all?
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 80.dp)
        // this applies to the container, if you wanna add something just do .whatever(int.dp) or something
    ) { // column as the base plate + for the effect of panels, use functions with rows and reference them here
        var text by remember {
            mutableStateOf("Type Here...")
        }
        OutlinedTextField(
            value = text,
            onValueChange ={ newText ->
                text = newText
            })

        // wierd but baiscally saves any change to textfield as var text, the rest is obvious
        // need to do a function that uh... onEnter = coolAPIfunction() !!

        Image(painter = painterResource(id = R.drawable.download),
            contentDescription = null,
            modifier = Modifier.padding(bottom = 15.dp)
                .padding(top= 15.dp)) // increase image size, this will do for now


        // these text elements are useless until i add the api call
        // but i don't want to ;( but ill do it later today if i can be bothered
        Text(text = "50 °C",
            modifier = Modifier.padding(bottom = 30.dp)
            ) // attach this to a function for uh... functionality

        Text(text = "Location: Melbourne",
            modifier = Modifier.padding(bottom = 10.dp))
        Text(text = "Hello $name!",
            modifier = Modifier.padding(bottom = 10.dp))
        Text(text = "sensible name",
            modifier = Modifier.padding(bottom = 10.dp))
        Text(text = "Hello $name!")
    }

}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    HealthApp2Theme {
        Greeting("Android")
    }
    // this function runs on startup
}