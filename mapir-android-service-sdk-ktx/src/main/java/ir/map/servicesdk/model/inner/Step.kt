package ir.map.servicesdk.model.inner

class Step(
        val intersections: List<Intersection>,
        val drivingSide: String,
        val geometry: String,
        val mode: String,
        val duration: Int,
        val maneuver: Maneuver,
        val weight: Int,
        val distance: Int,
        val name: String)