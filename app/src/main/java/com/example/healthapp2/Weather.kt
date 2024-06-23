import kotlinx.serialization.SerialName

// stored here to save me some effort
// import kotlinx.serialization.json.Json
// the above handles json files: using classes like the one below!
// import kotlinx.serialization.encodeToString
// haven't done this in ages, pretty sure it's used to grab certain parts of the json

data class Root(
    val coord: Coord,
    val weather: List<Weather>,
    val base: String,
    val main: Main,
    val visibility: Long,
    val wind: Wind,
    val rain: Rain,
    val clouds: Clouds,
    val dt: Long,
    val sys: Sys,
    val timezone: Long,
    val id: Long,
    val name: String,
    val cod: Long,
)

data class Coord(
    val lon: Double,
    val lat: Double,
)

data class Weather(
    val id: Long,
    val main: String,
    val description: String,
    val icon: String,
)

data class Main(
    val temp: Double,
    @SerialName("feels_like")
    val feelsLike: Double,
    @SerialName("temp_min")
    val tempMin: Double,
    @SerialName("temp_max")
    val tempMax: Double,
    val pressure: Long,
    val humidity: Long,
    @SerialName("sea_level")
    val seaLevel: Long,
    @SerialName("grnd_level")
    val grndLevel: Long,
)

data class Wind(
    val speed: Double,
    val deg: Long,
    val gust: Double,
)

data class Rain(
    @SerialName("1h")
    val n1h: Double,
)

data class Clouds(
    val all: Long,
)

data class Sys(
    val type: Long,
    val id: Long,
    val country: String,
    val sunrise: Long,
    val sunset: Long,
)