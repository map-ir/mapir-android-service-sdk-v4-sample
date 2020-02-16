package ir.map.servicesdk.response;

import org.json.JSONObject;

import ir.map.servicesdk.model.base.MapirResponse;
import ir.map.servicesdk.model.inner.Geom;

import static ir.map.servicesdk.MapService.createGeom;

public class PlaqueReverseGeoCodeResponse extends MapirResponse {

    private String address;
    private String postalAddress;
    private String addressCompact;
    private String last;
    private String name;
    private String poi;
    private String country;
    private String province;
    private String county;
    private String district;
    private String ruralDistrict;
    private String city;
    private String village;
    private String region;
    private String neighbourhood;
    private String primary;
    private String plaque;
    private String postalCode;
    private Geom geom;

    private PlaqueReverseGeoCodeResponse(String address, String postalAddress, String addressCompact, String last, String name, String poi, String country, String province, String county, String district, String ruralDistrict, String city, String village, String region, String neighbourhood, String primary, String plaque, String postalCode, Geom geom) {
        this.address = address;
        this.postalAddress = postalAddress;
        this.addressCompact = addressCompact;
        this.last = last;
        this.name = name;
        this.poi = poi;
        this.country = country;
        this.province = province;
        this.county = county;
        this.district = district;
        this.ruralDistrict = ruralDistrict;
        this.city = city;
        this.village = village;
        this.region = region;
        this.neighbourhood = neighbourhood;
        this.primary = primary;
        this.plaque = plaque;
        this.postalCode = postalCode;
        this.geom = geom;
    }

    public String getAddress() {
        return address;
    }

    public String getPostalAddress() {
        return postalAddress;
    }

    public String getAddressCompact() {
        return addressCompact;
    }

    public String getLast() {
        return last;
    }

    public String getName() {
        return name;
    }

    public String getPoi() {
        return poi;
    }

    public String getCountry() {
        return country;
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

    public String getRuralDistrict() {
        return ruralDistrict;
    }

    public String getCity() {
        return city;
    }

    public String getVillage() {
        return village;
    }

    public String getRegion() {
        return region;
    }

    public String getNeighbourhood() {
        return neighbourhood;
    }

    public String getPrimary() {
        return primary;
    }

    public String getPlaque() {
        return plaque;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public Geom getGeom() {
        return geom;
    }

    public static MapirResponse createPlaqueReverseGeoCodeResponse(String data) {
        try {
            JSONObject tempData = new JSONObject(data);

            return new PlaqueReverseGeoCodeResponse(
                    tempData.getString("address"),
                    tempData.getString("postal_address"),
                    tempData.getString("address_compact"),
                    tempData.getString("last"),
                    tempData.getString("name"),
                    tempData.getString("poi"),
                    tempData.getString("country"),
                    tempData.getString("province"),
                    tempData.getString("county"),
                    tempData.getString("district"),
                    tempData.getString("rural_district"),
                    tempData.getString("city"),
                    tempData.getString("village"),
                    tempData.getString("region"),
                    tempData.getString("neighbourhood"),
                    tempData.getString("primary"),
                    tempData.getString("plaque"),
                    tempData.getString("postal_code"),
                    createGeom(tempData.get("geom").toString())
            );
        } catch (Exception e) {
            return null;
        }
    }
}