package ir.map.servicesdk.model.inner;

public class ResultByDuration {

    private String originIndex;
    private String destinationIndex;
    private Double duration;

    public ResultByDuration(String originIndex, String destinationIndex, Double duration) {
        this.originIndex = originIndex;
        this.destinationIndex = destinationIndex;
        this.duration = duration;
    }

    public String getOriginIndex() {
        return originIndex;
    }

    public String getDestinationIndex() {
        return destinationIndex;
    }

    public Double getDuration() {
        return duration;
    }
}
