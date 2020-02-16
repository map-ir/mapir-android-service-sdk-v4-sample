package ir.map.servicesdk.model.inner;

import java.util.List;

public class RouteItem {

    private String geometry;
    private List<Leg> legs;
    private Double distance;
    private Double duration;
    private String weightName;
    private Double weight;

    public RouteItem(String geometry, Double distance, Double duration, String weightName, Double weight, List<Leg> legs) {
        this.geometry = geometry;
        this.distance = distance;
        this.duration = duration;
        this.weightName = weightName;
        this.weight = weight;
        this.legs = legs;
    }

    public String getGeometry() {
        return geometry;
    }

    public List<Leg> getLegs() {
        return legs;
    }

    public Double getDistance() {
        return distance;
    }

    public Double getDuration() {
        return duration;
    }

    public String getWeightName() {
        return weightName;
    }

    public Double getWeight() {
        return weight;
    }
}
