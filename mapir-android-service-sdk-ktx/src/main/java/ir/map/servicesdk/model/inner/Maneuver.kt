package ir.map.servicesdk.model.inner

class Maneuver(
        val bearingAfter: Int,
        val location: List<Double>,
        val bearingBefore: Int,
        val type: String,
        val modifier: String?
)