package ir.map.servicesdk.enums

enum class FilterOptions(private val value: String) {
    PROVINCE("province"),
    CITY("city"),
    COUNTY("county"),
    REGION("region"),
    NEIGHBOURHOOD("neighbourhood"),
    DISTANCE("distance"),
    POLYGON("polygon");

    override fun toString() = value
}