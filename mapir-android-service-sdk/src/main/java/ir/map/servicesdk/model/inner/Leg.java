package ir.map.servicesdk.model.inner;

import java.util.List;

public class Leg {

    private List<Step> steps;
    private Double distance;
    private Double duration;
    private String summary;
    private Double weight;

    public Leg(Double distance, Double duration, String summary, Double weight, List<Step> steps) {
        this.distance = distance;
        this.duration = duration;
        this.summary = summary;
        this.weight = weight;
        this.steps = steps;
    }

    public List<Step> getSteps() {
        return steps;
    }

    public Double getDistance() {
        return distance;
    }


    public Double getDuration() {
        return duration;
    }

    public String getSummary() {
        return summary;
    }

    public Double getWeight() {
        return weight;
    }
}
