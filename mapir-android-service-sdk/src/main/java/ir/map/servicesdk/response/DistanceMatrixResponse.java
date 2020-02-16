package ir.map.servicesdk.response;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import ir.map.servicesdk.model.base.MapirResponse;
import ir.map.servicesdk.model.inner.DistanceMatrixPointResponse;
import ir.map.servicesdk.model.inner.ResultByDistance;
import ir.map.servicesdk.model.inner.ResultByDuration;

public class DistanceMatrixResponse extends MapirResponse {

    private List<ResultByDistance> resultByDistance;
    private List<ResultByDuration> resultByDuration;
    private List<DistanceMatrixPointResponse> origins;
    private List<DistanceMatrixPointResponse> destinations;

    private DistanceMatrixResponse(
            List<ResultByDistance> resultByDistance,
            List<ResultByDuration> resultByDuration,
            List<DistanceMatrixPointResponse> origins,
            List<DistanceMatrixPointResponse> destinations) {
        this.resultByDistance = resultByDistance;
        this.resultByDuration = resultByDuration;
        this.origins = origins;
        this.destinations = destinations;
    }

    public static MapirResponse createDistanceMatrixResponse(String data) {
        try {
            JSONObject tempData = new JSONObject(data);

            List<ResultByDistance> resultByDistanceItems = new ArrayList<>();
            if (tempData.has("distance")) {
                JSONArray tempDistanceMatrixResultByDistanceItems = new JSONArray(tempData.get("distance").toString());
                for (int i = 0; i < tempDistanceMatrixResultByDistanceItems.length(); i++) {
                    JSONObject tempResultItem = new JSONObject(tempDistanceMatrixResultByDistanceItems.get(i).toString());

                    ResultByDistance item = new ResultByDistance(
                            tempResultItem.getString("origin_index"),
                            tempResultItem.getString("destination_index"),
                            tempResultItem.getDouble("distance")
                    );

                    resultByDistanceItems.add(item);
                }
            }

            List<ResultByDuration> resultByDurationItems = new ArrayList<>();
            if (tempData.has("duration")) {
                JSONArray tempDistanceMatrixResultByDurationItems = new JSONArray(tempData.get("duration").toString());
                for (int i = 0; i < tempDistanceMatrixResultByDurationItems.length(); i++) {
                    JSONObject tempResultItem = new JSONObject(tempDistanceMatrixResultByDurationItems.get(i).toString());

                    ResultByDuration item = new ResultByDuration(
                            tempResultItem.getString("origin_index"),
                            tempResultItem.getString("destination_index"),
                            tempResultItem.getDouble("duration")
                    );

                    resultByDurationItems.add(item);
                }
            }

            JSONObject tempDistanceMatrixOriginItems = new JSONObject(tempData.get("origins").toString());
            Iterator<String> tempDistanceMatrixOriginKeys = tempDistanceMatrixOriginItems.keys();
            List<DistanceMatrixPointResponse> originsItems = new ArrayList<>();

            while (tempDistanceMatrixOriginKeys.hasNext()) {
                String id = tempDistanceMatrixOriginKeys.next();
                JSONObject tempOriginItem = tempDistanceMatrixOriginItems.getJSONObject(id);

                DistanceMatrixPointResponse item = new DistanceMatrixPointResponse(
                        id,
                        tempOriginItem.getString("name"),
                        tempOriginItem.getString("province_name"),
                        tempOriginItem.getString("county_name"),
                        tempOriginItem.getString("district_title"),
                        tempOriginItem.getString("ruraldistrict_title"),
                        tempOriginItem.getString("suburb_title"),
                        tempOriginItem.getString("neighbourhood_title")
                );

                originsItems.add(item);
            }

            JSONObject tempDistanceMatrixDestinationItems = new JSONObject(tempData.get("destinations").toString());
            Iterator<String> tempDistanceMatrixDestinationKeys = tempDistanceMatrixDestinationItems.keys();
            List<DistanceMatrixPointResponse> destinationsItems = new ArrayList<>();

            while (tempDistanceMatrixDestinationKeys.hasNext()) {
                String id = tempDistanceMatrixDestinationKeys.next();
                JSONObject tempDestinationItem = tempDistanceMatrixDestinationItems.getJSONObject(id);

                DistanceMatrixPointResponse item = new DistanceMatrixPointResponse(
                        id,
                        tempDestinationItem.getString("name"),
                        tempDestinationItem.getString("province_name"),
                        tempDestinationItem.getString("county_name"),
                        tempDestinationItem.getString("district_title"),
                        tempDestinationItem.getString("ruraldistrict_title"),
                        tempDestinationItem.getString("suburb_title"),
                        tempDestinationItem.getString("neighbourhood_title")
                );

                destinationsItems.add(item);
            }

            return new DistanceMatrixResponse(
                    resultByDistanceItems,
                    resultByDurationItems,
                    originsItems,
                    destinationsItems
            );
        } catch (Exception e) {
            return null;
        }
    }

    public List<ResultByDistance> getResultByDistance() {
        return resultByDistance;
    }

    public List<ResultByDuration> getResultByDuration() {
        return resultByDuration;
    }

    public List<DistanceMatrixPointResponse> getOrigins() {
        return origins;
    }

    public List<DistanceMatrixPointResponse> getDestinations() {
        return destinations;
    }
}