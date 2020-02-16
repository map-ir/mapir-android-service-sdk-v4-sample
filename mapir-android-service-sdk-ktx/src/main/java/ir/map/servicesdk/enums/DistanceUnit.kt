package ir.map.servicesdk.enums

enum class DistanceUnit(private val value: String) {
    KILOMETER("km"),
    METER("m");

    override fun toString() = value
}