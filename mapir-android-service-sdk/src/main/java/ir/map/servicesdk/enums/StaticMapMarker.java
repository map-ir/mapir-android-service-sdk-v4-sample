package ir.map.servicesdk.enums;

public enum StaticMapMarker {
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

    private String value;

    StaticMapMarker(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
