package ir.map.servicesdk.model.inner

class RouteItem(
        val geometry: String,
        val distance: Double,
        val duration: Double,
        val weightName: String,
        val weight: Double,
        val legs: List<Leg>
)