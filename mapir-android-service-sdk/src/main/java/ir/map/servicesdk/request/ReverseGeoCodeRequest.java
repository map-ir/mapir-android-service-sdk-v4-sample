package ir.map.servicesdk.request;

import ir.map.servicesdk.model.base.BaseModel;

public class ReverseGeoCodeRequest extends BaseModel {

    private double latitude;
    private double longitude;

    public ReverseGeoCodeRequest(double latitude, double longitude) {
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
