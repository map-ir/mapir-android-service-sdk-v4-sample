package ir.map.servicesdk.request;

import ir.map.servicesdk.model.base.BaseModel;

public class GeofenceRequest extends BaseModel {

    private double latitude;
    private double longitude;

    public GeofenceRequest(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
