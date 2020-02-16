package ir.map.servicesdk.model.inner;

public class SearchItem {

    private String province;
    private String county;
    private String district;
    private String city;
    private String region;
    private String neighborhood;
    private String title;
    private String address;
    private String type;
    private String fclass;
    private Geom geom;

    public SearchItem(String province, String county, String district, String city, String region, String neighborhood, String title, String address, String type, String fclass, Geom geom) {
        this.province = province;
        this.county = county;
        this.district = district;
        this.city = city;
        this.region = region;
        this.neighborhood = neighborhood;
        this.title = title;
        this.address = address;
        this.type = type;
        this.fclass = fclass;
        this.geom = geom;
    }

    public String getProvince() {
        return province;
    }

    public String getCounty() {
        return county;
    }

    public String getDistrict() {
        return district;
    }

    public String getCity() {
        return city;
    }

    public String getRegion() {
        return region;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public String getTitle() {
        return title;
    }

    public String getAddress() {
        return address;
    }

    public String getType() {
        return type;
    }

    public String getFclass() {
        return fclass;
    }

    public Geom getGeom() {
        return geom;
    }
}
