package ir.map.servicesdk.enums

enum class StaticMapMarker(private val value: String) {
    DEFAULT("default"),
    RED("red"),
    ORANGE("orange"),
    PINK("pink"),
    GREEN("green"),
    BLUE("blue"),
    SKY_BLUE("skyblue"),
    NAVY_BLUE("navyblue"),
    YELLOW("yellow"),
    BLACK("black"),
    WHITE("white"),
    MAGENTA("magenta"),
    TEAL("teal"),
    ORIGIN("origin"),
    DESTINATION("destination");

    override fun toString() = value
}