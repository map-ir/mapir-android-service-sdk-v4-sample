package ir.map.servicesdk.model.inner

class Leg(
        val distance: Double,
        val duration: Double,
        val summary: String,
        val weight: Double,
        val steps: List<Step>
)