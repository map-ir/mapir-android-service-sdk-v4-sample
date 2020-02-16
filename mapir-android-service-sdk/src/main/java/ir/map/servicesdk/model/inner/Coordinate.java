package ir.map.servicesdk.model.inner;

import ir.map.servicesdk.model.base.MapirResponse;

public class Coordinate extends MapirResponse {

    private Double latitude;
    private Double longitude;

    public Coordinate(Double latitude, Double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }
}

