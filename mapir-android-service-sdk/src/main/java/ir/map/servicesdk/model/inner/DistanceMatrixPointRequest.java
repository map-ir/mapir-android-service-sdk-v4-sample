package ir.map.servicesdk.model.inner;

public class DistanceMatrixPointRequest {

    private String id;
    private double latitude;
    private double longitude;

    public DistanceMatrixPointRequest(String id, double latitude, double longitude) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public DistanceMatrixPointRequest(int id, double latitude, double longitude) {
        this.id = String.valueOf(id);
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getId() {
        return id;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
