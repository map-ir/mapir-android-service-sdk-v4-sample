package ir.map.servicesdk.model.inner;

public class ResultByDistance {

    private String originIndex;
    private String destinationIndex;
    private Double distance;

    public ResultByDistance(String originIndex, String destinationIndex, Double distance) {
        this.originIndex = originIndex;
        this.destinationIndex = destinationIndex;
        this.distance = distance;
    }

    public String getOriginIndex() {
        return originIndex;
    }

    public String getDestinationIndex() {
        return destinationIndex;
    }

    public Double getDistance() {
        return distance;
    }
}
