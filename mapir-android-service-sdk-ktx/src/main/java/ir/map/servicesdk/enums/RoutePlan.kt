package ir.map.servicesdk.enums

enum class RoutePlan(private val value: String) {
    SIMPLE(""),
    TRAFFIC("tarh"),
    EVEN_ODD("zojofard");

    override fun toString() = value
}