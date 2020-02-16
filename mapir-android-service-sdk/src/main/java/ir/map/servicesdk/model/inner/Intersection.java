package ir.map.servicesdk.model.inner;

import java.util.List;

public class Intersection {
    private Integer out;
    private List<Boolean> entry;
    private List<Integer> bearings;
    private List<Double> location;
    private Integer in;

    public Intersection(Integer in, Integer out, List<Boolean> entry, List<Integer> bearings, List<Double> location) {
        this.in = in;
        this.out = out;
        this.entry = entry;
        this.bearings = bearings;
        this.location = location;
    }

    public Integer getOut() {
        return out;
    }

    public List<Boolean> getEntry() {
        return entry;
    }

    public List<Integer> getBearings() {
        return bearings;
    }

    public List<Double> getLocation() {
        return location;
    }

    public Integer getIn() {
        return in;
    }
}
