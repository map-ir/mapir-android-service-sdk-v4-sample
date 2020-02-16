package ir.map.servicesdk.model.inner;

public class Geom {

    private String type;
    private Double[] coordinates = null;

    public Geom(String type, Double[] coordinates) {
        this.type = type;
        this.coordinates = coordinates;
    }

    public String getType() {
        return type;
    }

    public Double[] getCoordinates() {
        return coordinates;
    }

    public Double getLatitude() {
        return coordinates[1];
    }

    public Double getLongitude() {
        return coordinates[0];
    }
}
