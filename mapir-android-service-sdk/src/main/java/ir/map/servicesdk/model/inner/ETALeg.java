package ir.map.servicesdk.model.inner;

public class ETALeg {

    private Double distance;
    private Double duration;

    public ETALeg(Double distance, Double duration) {
        this.distance = distance;
        this.duration = duration;
    }

    public Double getDistance() {
        return distance;
    }

    public Double getDuration() {
        return duration;
    }
}
