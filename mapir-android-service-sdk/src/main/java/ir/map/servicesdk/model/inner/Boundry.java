package ir.map.servicesdk.model.inner;

import java.util.List;

public class Boundry {

    private String type;
    private List<List<Coordinate>> coordinates;

    public Boundry(String type, List<List<Coordinate>> coordinates) {
        this.type = type;
        this.coordinates = coordinates;
    }

    public String getType() {
        return type;
    }

    public List<List<Coordinate>> getCoordinates() {
        return coordinates;
    }
}

