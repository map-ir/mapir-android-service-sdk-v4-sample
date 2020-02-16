package ir.map.servicesdk.enums;

public enum SelectOptions {
    PROVINCE("province"),
    CITY("city"),
    COUNTY("county"),
    REGION("region"),
    NEIGHBORHOOD("neighborhood"),
    LAND_USE("landuse"),
    WOOD_WATER("woodwater"),
    ROADS("roads"),
    POI("poi"),
    NEARBY("nearby"),
    NATURAL("natural");

    private String value;

    SelectOptions(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
