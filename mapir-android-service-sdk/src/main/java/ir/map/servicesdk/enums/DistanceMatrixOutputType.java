package ir.map.servicesdk.enums;

public enum DistanceMatrixOutputType {
    DISTANCE("distance"),
    DURATION("duration");

    private String value;

    DistanceMatrixOutputType(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
