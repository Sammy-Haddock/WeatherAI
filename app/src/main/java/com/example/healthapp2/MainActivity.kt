package com.example.healthapp2

import android.os.AsyncTask
import android.os.Bundle
//import android.provider.ContactsContract.CommonDataKinds.Website.URL
//import android.view.View
//import android.widget.ProgressBar
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.material3.OutlinedTextField
//import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.healthapp2.ui.theme.HealthApp2Theme
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import org.json.JSONObject
import java.net.URL
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainActivity : ComponentActivity() {

    val CITY: String = "london,gb"
    val API: String = "a54af70bd3335371e6ccc49d6c96e645"
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
        weatherTask().execute()
    }
    inner class weatherTask() : AsyncTask<String, Void, String>() {
        override fun onPreExecute() {
            super.onPreExecute()
        }

        override fun doInBackground(vararg p0: String?): String? {
            var response: String?
            try {
                response = URL("https://api.openweathermap.org/data/2.5/weather?q=$CITY&units=metric&appid=$API")
                    .readText(Charsets.UTF_8)
            }
            catch (e: Exception)
            {
                response = null
            }
            return response
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            try {
                val jsonObj = JSONObject(result)
                val main = jsonObj.getJSONObject("main")
                val sys = jsonObj.getJSONObject("sys")
                val wind = jsonObj.getJSONObject("wind")
                val weather = jsonObj.getJSONArray("weather").getJSONObject(0)
                val updatedAt:Long = jsonObj.getLong("dt")
                val updatedAtText = "Updated at: "+ SimpleDateFormat("dd/MM/yyyy hh:mm a", Locale.ENGLISH).format(Date(updatedAt*1000))
                val temp = main.getString("temp")+"°C"
                val tempMin = "Min Temp: "+main.getString("temp_min")+"°C"
                val tempMax = "Max Temp: "+main.getString("temp_max")+"°C"
                val pressure = main.getString("pressure")
                val humidity = main.getString("humidity")
                val sunrise:Long = sys.getLong("sunrise")
                val sunset:Long = sys.getLong("sunset")
                val windSpeed = wind.getString("speed")
                val weatherDescription = weather.getString("description")
                val address = jsonObj.getString("name")+", "+sys.getString("country")

                // do findViewById<example>(R.id.haha).text = "noo!!!"

            } catch (e: Exception) {
                print("You suck!")
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