package ir.map.servicesdk.model.inner;

public class WayPoint {

    private String hint;
    private Double distance;
    private String name;
    private Double latitude;
    private Double longitude;

    public WayPoint(String hint, Double distance, String name, Double latitude, Double longitude) {
        this.hint = hint;
        this.distance = distance;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getHint() {
        return hint;
    }

    public Double getDistance() {
        return distance;
    }

    public String getName() {
        return name;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }
}
