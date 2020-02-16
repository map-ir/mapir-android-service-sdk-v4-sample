package ir.map.servicesdk.enums;

public enum FilterOptions {
    PROVINCE("province"),
    CITY("city"),
    COUNTY("county"),
    REGION("region"),
    NEIGHBOURHOOD("neighborhood"),
    DISTANCE("distance"),
    POLYGON("polygon");

    private String value;

    FilterOptions(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
