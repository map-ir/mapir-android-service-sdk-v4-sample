package ir.map.servicesdk.enums

enum class SelectOptions(private val value: String) {
    PROVINCE("province"),
    CITY("city"),
    COUNTY("county"),
    REGION("region"),
    NEIGHBOURHOOD("neighborhood"),
    LAND_USE("landuse"),
    WOOD_WATER("woodwater"),
    ROADS("roads"),
    POI("poi"),
    NEARBY("nearby"),
    NATURAL("natural");

    override fun toString() = value
}