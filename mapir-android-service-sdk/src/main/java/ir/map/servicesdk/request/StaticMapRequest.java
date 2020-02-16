package ir.map.servicesdk.request;

import ir.map.servicesdk.enums.StaticMapMarker;
import ir.map.servicesdk.model.base.BaseModel;

public class StaticMapRequest extends BaseModel {

    private double latitude;
    private double longitude;
    private int width;
    private int height;
    private int zoom;
    private String label;
    private StaticMapMarker color;

    public StaticMapRequest(double latitude, double longitude, int width, int height, int zoom, String label, StaticMapMarker color) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.width = width;
        this.height = height;
        this.label = label;
        this.color = color;

        if (zoom < 1 || zoom > 20)
            throw new RuntimeException("Zomm level for static map api must be between 1 and 20; (can't be " + zoom);
        else
            this.zoom = zoom;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getZoom() {
        return zoom;
    }

    public String getLabel() {
        return label;
    }

    public StaticMapMarker getColor() {
        return color;
    }
}
