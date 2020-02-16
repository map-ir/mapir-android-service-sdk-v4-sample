package ir.map.servicesdk.response;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import ir.map.servicesdk.model.base.MapirResponse;
import ir.map.servicesdk.model.inner.SearchItem;

import static ir.map.servicesdk.MapService.createGeom;

public class SearchResponse extends MapirResponse {

    private Integer count;
    private List<SearchItem> searchItems;

    private SearchResponse(int count, List<SearchItem> searchItems) {
        this.count = count;
        this.searchItems = searchItems;
    }

    public Integer getCount() {
        return count;
    }

    public List<SearchItem> getSearchItems() {
        return searchItems;
    }

    public static MapirResponse createSearchResponse(String data) {
        try {
            JSONObject tempData = new JSONObject(data);
            JSONArray tempSearchItems = new JSONArray(tempData.get("value").toString());

            List<SearchItem> searchItems = new ArrayList<>();
            for (int i = 0; i < tempSearchItems.length(); i++) {
                JSONObject tempSearchItem = new JSONObject(tempSearchItems.get(i).toString());

                SearchItem item = new SearchItem(
                        tempSearchItem.getString("province"),
                        tempSearchItem.getString("county"),
                        tempSearchItem.getString("district"),
                        tempSearchItem.getString("city"),
                        tempSearchItem.getString("region"),
                        tempSearchItem.getString("neighborhood"),
                        tempSearchItem.getString("title"),
                        tempSearchItem.getString("address"),
                        tempSearchItem.getString("type"),
                        tempSearchItem.getString("fclass"),
                        createGeom(tempSearchItem.get("geom").toString())
                );

                searchItems.add(item);
            }

            return new SearchResponse(
                    tempData.getInt("odata.count"),
                    searchItems
            );
        } catch (Exception e) {
            return null;
        }
    }
}