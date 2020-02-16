package ir.map.servicesdk.enums

enum class RouteOverView(private val value: String) {
    FULL("full"),
    SIMPLE("simplified"),
    NONE("false");

    override fun toString() = value
}