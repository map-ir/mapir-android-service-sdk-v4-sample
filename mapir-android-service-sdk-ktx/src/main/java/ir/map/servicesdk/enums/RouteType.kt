package ir.map.servicesdk.enums

enum class RouteType(private val value: String) {
    DRIVING("route"),
    BICYCLE("bicycle"),
    WALKING("foot");

    override fun toString() = value
}