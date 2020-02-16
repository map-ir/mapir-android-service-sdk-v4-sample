package ir.map.servicesdk.response;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import ir.map.servicesdk.model.base.MapirResponse;
import ir.map.servicesdk.model.inner.ETALeg;

public class EstimatedTimeArrivalResponse extends MapirResponse {

    private Double distance;
    private Double duration;
    private List<ETALeg> legs;

    private EstimatedTimeArrivalResponse(Double distance, Double duration, List<ETALeg> legs) {
        this.distance = distance;
        this.duration = duration;
        this.legs = legs;
    }

    public static MapirResponse createEstimatedTimeArrivalResponse(String data) {
        try {
            JSONObject tempData = new JSONObject(data);
            JSONObject tempRoutes = new JSONObject(tempData.get("routes").toString());

            JSONArray tempLegs = new JSONArray(tempRoutes.get("legs").toString());
            List<ETALeg> legItems = new ArrayList<>();
            for (int i = 0; i < tempLegs.length(); i++) {
                JSONObject tempLeg = new JSONObject(tempLegs.get(i).toString());

                legItems.add(
                        new ETALeg(
                                tempLeg.getDouble("distance"),
                                tempLeg.getDouble("duration")
                        )
                );
            }

            return new EstimatedTimeArrivalResponse(
                    tempRoutes.getDouble("distance"),
                    tempRoutes.getDouble("duration"),
                    legItems
            );
        } catch (Exception e) {
            return null;
        }
    }

    public Double getDistance() {
        return distance;
    }

    public Double getDuration() {
        return duration;
    }

    public List<ETALeg> getLegs() {
        return legs;
    }
}

