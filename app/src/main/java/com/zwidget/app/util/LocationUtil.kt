package com.zwidget.app.util

object LocationUtil {

    data class LocationCoordinates(
        val latitude: Double,
        val longitude: Double,
        val name: String
    )

    // Predefined coordinates for common cities
    private val cityCoordinates = mapOf(
        "London" to LocationCoordinates(51.5074, -0.1278, "London"),
        "New York" to LocationCoordinates(40.7128, -74.0060, "New York"),
        "Tokyo" to LocationCoordinates(35.6762, 139.6503, "Tokyo"),
        "Sydney" to LocationCoordinates(-33.8688, 151.2093, "Sydney"),
        "Paris" to LocationCoordinates(48.8566, 2.3522, "Paris"),
        "Berlin" to LocationCoordinates(52.5200, 13.4050, "Berlin"),
        "Hong Kong" to LocationCoordinates(22.3193, 114.1694, "Hong Kong"),
        "Singapore" to LocationCoordinates(1.3521, 103.8198, "Singapore"),
        "Dubai" to LocationCoordinates(25.2048, 55.2708, "Dubai"),
        "Los Angeles" to LocationCoordinates(34.0522, -118.2437, "Los Angeles"),
        "San Francisco" to LocationCoordinates(37.7749, -122.4194, "San Francisco"),
        "Chicago" to LocationCoordinates(41.8781, -87.6298, "Chicago"),
        "Toronto" to LocationCoordinates(43.6532, -79.3832, "Toronto"),
        "Mumbai" to LocationCoordinates(19.0760, 72.8777, "Mumbai"),
        "Shanghai" to LocationCoordinates(31.2304, 121.4737, "Shanghai"),
        "Beijing" to LocationCoordinates(39.9042, 116.4074, "Beijing"),
        "Moscow" to LocationCoordinates(55.7558, 37.6173, "Moscow"),
        "São Paulo" to LocationCoordinates(-23.5505, -46.6333, "São Paulo"),
        "Mexico City" to LocationCoordinates(19.4326, -99.1332, "Mexico City"),
        "Cairo" to LocationCoordinates(30.0444, 31.2357, "Cairo"),
        "Lagos" to LocationCoordinates(6.5244, 3.3792, "Lagos"),
        "Istanbul" to LocationCoordinates(41.0082, 28.9784, "Istanbul"),
        "Bangkok" to LocationCoordinates(13.7563, 100.5018, "Bangkok"),
        "Seoul" to LocationCoordinates(37.5665, 126.9780, "Seoul"),
        "Jakarta" to LocationCoordinates(-6.2088, 106.8456, "Jakarta"),
        "Manila" to LocationCoordinates(14.5995, 120.9842, "Manila"),
        "Amsterdam" to LocationCoordinates(52.3676, 4.9041, "Amsterdam"),
        "Madrid" to LocationCoordinates(40.4168, -3.7038, "Madrid"),
        "Rome" to LocationCoordinates(41.9028, 12.4964, "Rome"),
        "Vienna" to LocationCoordinates(48.2082, 16.3738, "Vienna"),
        "Athens" to LocationCoordinates(37.9838, 23.7275, "Athens"),
        "Lisbon" to LocationCoordinates(38.7223, -9.1393, "Lisbon"),
        "Stockholm" to LocationCoordinates(59.3293, 18.0686, "Stockholm"),
        "Copenhagen" to LocationCoordinates(55.6761, 12.5683, "Copenhagen"),
        "Oslo" to LocationCoordinates(59.9139, 10.7522, "Oslo"),
        "Helsinki" to LocationCoordinates(60.1699, 24.9384, "Helsinki"),
        "Warsaw" to LocationCoordinates(52.2297, 21.0122, "Warsaw"),
        "Prague" to LocationCoordinates(50.0755, 14.4378, "Prague"),
        "Budapest" to LocationCoordinates(47.4979, 19.0402, "Budapest"),
        "Zurich" to LocationCoordinates(47.3769, 8.5417, "Zurich"),
        "Brussels" to LocationCoordinates(50.8503, 4.3517, "Brussels"),
        "Dublin" to LocationCoordinates(53.3498, -6.2603, "Dublin"),
        "Edinburgh" to LocationCoordinates(55.9533, -3.1883, "Edinburgh"),
        "Barcelona" to LocationCoordinates(41.3851, 2.1734, "Barcelona"),
        "Milan" to LocationCoordinates(45.4642, 9.1900, "Milan"),
        "Munich" to LocationCoordinates(48.1351, 11.5820, "Munich"),
        "Vancouver" to LocationCoordinates(49.2827, -123.1207, "Vancouver"),
        "Montreal" to LocationCoordinates(45.5017, -73.5673, "Montreal"),
        "Boston" to LocationCoordinates(42.3601, -71.0589, "Boston"),
        "Seattle" to LocationCoordinates(47.6062, -122.3321, "Seattle"),
        "Miami" to LocationCoordinates(25.7617, -80.1918, "Miami"),
        "Las Vegas" to LocationCoordinates(36.1699, -115.1398, "Las Vegas"),
        "Phoenix" to LocationCoordinates(33.4484, -112.0740, "Phoenix"),
        "Denver" to LocationCoordinates(39.7392, -104.9903, "Denver"),
        "Atlanta" to LocationCoordinates(33.7490, -84.3880, "Atlanta"),
        "Dallas" to LocationCoordinates(32.7767, -96.7970, "Dallas"),
        "Houston" to LocationCoordinates(29.7604, -95.3698, "Houston"),
        "Philadelphia" to LocationCoordinates(39.9526, -75.1652, "Philadelphia"),
        "Washington DC" to LocationCoordinates(38.9072, -77.0369, "Washington DC"),
        "Auckland" to LocationCoordinates(-36.8485, 174.7633, "Auckland"),
        "Wellington" to LocationCoordinates(-41.2865, 174.7762, "Wellington"),
        "Melbourne" to LocationCoordinates(-37.8136, 144.9631, "Melbourne"),
        "Brisbane" to LocationCoordinates(-27.4698, 153.0251, "Brisbane"),
        "Perth" to LocationCoordinates(-31.9505, 115.8605, "Perth")
    )

    fun getCoordinates(cityName: String): LocationCoordinates? {
        return cityCoordinates[cityName]
    }

    fun getAllCities(): List<String> {
        return cityCoordinates.keys.sorted()
    }
}
