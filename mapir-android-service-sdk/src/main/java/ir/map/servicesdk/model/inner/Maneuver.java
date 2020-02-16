package ir.map.servicesdk.model.inner;

import java.util.List;

public class Maneuver {

    private Integer bearingAfter;
    private List<Double> location;
    private Integer bearingBefore;
    private String type;
    private String modifier;

    public Maneuver(Integer bearingAfter, List<Double> location, Integer bearingBefore, String type, String modifier) {
        this.bearingAfter = bearingAfter;
        this.location = location;
        this.bearingBefore = bearingBefore;
        this.type = type;
        this.modifier = modifier;
    }

    public Integer getBearingAfter() {
        return bearingAfter;
    }

    public List<Double> getLocation() {
        return location;
    }

    public Integer getBearingBefore() {
        return bearingBefore;
    }

    public String getType() {
        return type;
    }

    public String getModifier() {
        return modifier;
    }
}
