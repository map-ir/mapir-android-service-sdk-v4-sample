package ir.map.servicesdk.model.inner;

public class DistanceMatrixPointResponse {

    private String id;
    private String name;
    private String provinceName;
    private String countyName;
    private String districtTitle;
    private String ruralDistrictTitle;
    private String suburbTitle;
    private String neighbourhoodTitle;

    public DistanceMatrixPointResponse(
            String id,
            String name,
            String provinceName,
            String countyName,
            String districtTitle,
            String ruraldistrictTitle,
            String suburbTitle,
            String neighbourhoodTitle) {
        this.id = id;
        this.name = name;
        this.provinceName = provinceName;
        this.countyName = countyName;
        this.districtTitle = districtTitle;
        this.ruralDistrictTitle = ruraldistrictTitle;
        this.suburbTitle = suburbTitle;
        this.neighbourhoodTitle = neighbourhoodTitle;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public String getCountyName() {
        return countyName;
    }

    public String getDistrictTitle() {
        return districtTitle;
    }

    public String getRuralDistrictTitle() {
        return ruralDistrictTitle;
    }

    public String getSuburbTitle() {
        return suburbTitle;
    }

    public String getNeighbourhoodTitle() {
        return neighbourhoodTitle;
    }
}
