package ir.map.servicesdk.enums

enum class DistanceMatrixOutputType(private val value: String) {
    DISTANCE("distance"),
    DURATION("duration");

    override fun toString() = value
}