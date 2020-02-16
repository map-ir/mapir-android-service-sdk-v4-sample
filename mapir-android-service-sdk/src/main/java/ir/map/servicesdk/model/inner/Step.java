package ir.map.servicesdk.model.inner;

import java.util.List;

public class Step {

    private List<Intersection> intersections;
    private String drivingSide;
    private String geometry;
    private String mode;
    private Integer duration;
    private Maneuver maneuver;
    private Integer weight;
    private Integer distance;
    private String name;

    public Step(List<Intersection> intersections, String drivingSide, String geometry, String mode, Integer duration, Maneuver maneuver, Integer weight, Integer distance, String name) {
        this.intersections = intersections;
        this.drivingSide = drivingSide;
        this.geometry = geometry;
        this.mode = mode;
        this.duration = duration;
        this.maneuver = maneuver;
        this.weight = weight;
        this.distance = distance;
        this.name = name;
    }

    public List<Intersection> getIntersections() {
        return intersections;
    }

    public String getDrivingSide() {
        return drivingSide;
    }

    public String getGeometry() {
        return geometry;
    }

    public String getMode() {
        return mode;
    }

    public Integer getDuration() {
        return duration;
    }

    public Maneuver getManeuver() {
        return maneuver;
    }

    public Integer getWeight() {
        return weight;
    }

    public Integer getDistance() {
        return distance;
    }

    public String getName() {
        return name;
    }
}
