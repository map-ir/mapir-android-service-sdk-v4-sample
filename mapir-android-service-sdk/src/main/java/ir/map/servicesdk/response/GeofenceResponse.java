package ir.map.servicesdk.response;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import ir.map.servicesdk.model.base.MapirResponse;
import ir.map.servicesdk.model.inner.Boundry;
import ir.map.servicesdk.model.inner.Coordinate;
import ir.map.servicesdk.model.inner.GeofenceData;

public class GeofenceResponse extends MapirResponse {

    private int count;
    private List<GeofenceData> geofenceData;

    private GeofenceResponse(int count, List<GeofenceData> geofenceData) {
        this.count = count;
        this.geofenceData = geofenceData;
    }

    public int getCount() {
        return count;
    }

    public List<GeofenceData> getGeofenceData() {
        return geofenceData;
    }

    public static MapirResponse createGeofenceResponse(String data) {
        try {
            JSONObject tempData = new JSONObject(data);

            JSONArray tempGeofenceData = new JSONArray(tempData.get("value").toString());
            List<GeofenceData> geofenceData = new ArrayList<>();
            for (int i = 0; i < tempGeofenceData.length(); i++) {
                JSONObject tempGeofenceItem = new JSONObject(tempGeofenceData.get(i).toString());

                //region boundry
                JSONObject tempBoundry = new JSONObject(tempGeofenceItem.get("boundary").toString());

                //region coordinates
                JSONArray tempCoordinates = new JSONArray(tempBoundry.get("coordinates").toString());
                List<List<Coordinate>> coordinateCollection = new ArrayList<>();
                for (int m = 0; m < tempCoordinates.length(); m++) {
                    JSONArray tempList = tempCoordinates.getJSONArray(m);
                    List<Coordinate> coordinateList = new ArrayList<>();
                    for (int s = 0; s < tempList.length(); s++) {
                        Coordinate coordinate = new Coordinate(
                                tempList.getJSONArray(s).getDouble(1),
                                tempList.getJSONArray(s).getDouble(0)
                        );

                        coordinateList.add(coordinate);
                    }

                    coordinateCollection.add(coordinateList);
                }
                //endregion coordinates

                Boundry boundry = new Boundry(
                        tempBoundry.getString("type"),
                        coordinateCollection
                );
                //endregion boundry

                GeofenceData item = new GeofenceData(
                        tempGeofenceItem.getInt("id"),
                        boundry,
                        tempGeofenceItem.getString("meta"),
                        tempGeofenceItem.getString("created_at"),
                        tempGeofenceItem.getString("updated_at")
                );

                geofenceData.add(item);
            }

            return new GeofenceResponse(
                    tempData.getInt("odata.count"),
                    geofenceData
            );
        } catch (Exception e) {
            return null;
        }
    }
}