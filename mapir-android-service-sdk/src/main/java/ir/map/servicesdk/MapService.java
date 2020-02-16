package ir.map.servicesdk;

import android.os.Handler;
import android.os.Looper;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

import ir.map.servicesdk.enums.StaticMapMarker;
import ir.map.servicesdk.model.base.MapirError;
import ir.map.servicesdk.model.base.MapirResponse;
import ir.map.servicesdk.model.inner.Geom;
import ir.map.servicesdk.request.DistanceMatrixRequest;
import ir.map.servicesdk.request.EstimatedTimeArrivalRequest;
import ir.map.servicesdk.request.RouteRequest;
import ir.map.servicesdk.request.SearchRequest;
import ir.map.servicesdk.request.StaticMapRequest;
import ir.map.servicesdk.response.AutoCompleteSearchResponse;
import ir.map.servicesdk.response.DistanceMatrixResponse;
import ir.map.servicesdk.response.EstimatedTimeArrivalResponse;
import ir.map.servicesdk.response.FastReverseGeoCodeResponse;
import ir.map.servicesdk.response.GeofenceResponse;
import ir.map.servicesdk.response.PlaqueReverseGeoCodeResponse;
import ir.map.servicesdk.response.ReverseGeoCodeResponse;
import ir.map.servicesdk.response.RouteResponse;
import ir.map.servicesdk.response.SearchResponse;
import ir.map.servicesdk.response.StaticMapResponse;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static ir.map.servicesdk.HttpUtils.createError;
import static ir.map.servicesdk.HttpUtils.createResponse;
import static ir.map.servicesdk.UrlBuilder.AUTOCOMPLETE_SEARCH;
import static ir.map.servicesdk.UrlBuilder.DISTANCE_MATRIX;
import static ir.map.servicesdk.UrlBuilder.ETA;
import static ir.map.servicesdk.UrlBuilder.FAST_REVERSE;
import static ir.map.servicesdk.UrlBuilder.GEOFENCE;
import static ir.map.servicesdk.UrlBuilder.PLAQUE_REVERSE;
import static ir.map.servicesdk.UrlBuilder.REVERSE;
import static ir.map.servicesdk.UrlBuilder.ROUTE;
import static ir.map.servicesdk.UrlBuilder.SEARCH;
import static ir.map.servicesdk.UrlBuilder.STATIC_MAP;
import static ir.map.servicesdk.UrlBuilder.distanceMatrixUrl;
import static ir.map.servicesdk.UrlBuilder.estimatedTimeArrivalUrl;
import static ir.map.servicesdk.UrlBuilder.geofenceUrl;
import static ir.map.servicesdk.UrlBuilder.reverseGeoCodeUrl;
import static ir.map.servicesdk.UrlBuilder.routeUrl;
import static ir.map.servicesdk.UrlBuilder.searchUrl;
import static ir.map.servicesdk.UrlBuilder.staticMapUrl;

/**
 * MapService prepares map.ir services
 *
 * @author Morteza Hosseini
 * @version 4.0.0
 * @since 2020-01-15
 */
public class MapService {

    //region Initialize
    private OkHttpClient client = new OkHttpClient();
    //endregion Initialize

    //region Methods

    /**
     * This method is used to get reverse geocode response.
     *
     * @param latitude  This latitude in double
     * @param longitude This longitude in double
     * @param listener  This is callback we use to return response
     */
    public void reverseGeoCode(double latitude, double longitude, ResponseListener<ReverseGeoCodeResponse> listener) {
        execute(listener, reverseGeoCodeUrl(REVERSE, latitude, longitude), REVERSE);
    }

    /**
     * This method is used to get fast reverse geocode response.
     *
     * @param latitude  This latitude in double
     * @param longitude This longitude in double
     * @param listener  This is callback we use to return response
     */
    public void fastReverseGeoCode(double latitude, double longitude, ResponseListener<FastReverseGeoCodeResponse> listener) {
        execute(listener, reverseGeoCodeUrl(FAST_REVERSE, latitude, longitude), FAST_REVERSE);
    }

    /**
     * This method is used to get plaque reverse geocode response.
     *
     * @param latitude  This latitude in double
     * @param longitude This longitude in double
     * @param listener  This is callback we use to return response
     */
    public void plaqueReverseGeoCode(double latitude, double longitude, ResponseListener<PlaqueReverseGeoCodeResponse> listener) {
        execute(listener, reverseGeoCodeUrl(PLAQUE_REVERSE, latitude, longitude), PLAQUE_REVERSE);
    }

    /**
     * This method is used to get search response.
     *
     * @param requestBody This is object of type SearchRequest
     * @param listener    This is callback we use to return response
     */
    public void search(SearchRequest requestBody, ResponseListener<SearchResponse> listener) {
        execute(listener, searchUrl(SEARCH, requestBody), SEARCH);
    }

    /**
     * This method is used to get autocomplete search response.
     *
     * @param requestBody This is object of type {@link SearchRequest}
     * @param listener    This is callback we use to return response
     */
    public void autoCompleteSearch(SearchRequest requestBody, ResponseListener<AutoCompleteSearchResponse> listener) {
        execute(listener, searchUrl(AUTOCOMPLETE_SEARCH, requestBody), AUTOCOMPLETE_SEARCH);
    }

    /**
     * This method is used to get route response.
     *
     * @param requestBody This is object of type {@link RouteRequest}
     * @param listener    This is callback we use to return response
     */
    public void route(RouteRequest requestBody, ResponseListener<RouteResponse> listener) {
        execute(listener, routeUrl(requestBody), ROUTE);
    }

    /**
     * This method is used to get static map response.
     *
     * @param latitude  This latitude in double
     * @param longitude This longitude in double
     * @param width     This is width of created static map image
     * @param height    This is height of created static map image
     * @param zoom      This is zoom-level of created static map image
     * @param label     This is label of created static map image positioned on bottom of marker
     * @param color     This is color or type of marker positioned on specific point: {@link StaticMapMarker}
     * @param listener  This is callback we use to return response
     */
    public void staticMap(Double latitude, Double longitude, int width, int height, int zoom, String label, StaticMapMarker color, ResponseListener<StaticMapResponse> listener) {
        execute(listener, staticMapUrl(new StaticMapRequest(latitude, longitude, width, height, zoom, label, color)), STATIC_MAP);
    }

    /**
     * This method is used to get distance matrix response.
     *
     * @param requestBody This is object of type {@link DistanceMatrixRequest}
     * @param listener    This is callback we use to return response
     */
    public void distanceMatrix(DistanceMatrixRequest requestBody, ResponseListener<DistanceMatrixResponse> listener) {
        execute(listener, distanceMatrixUrl(requestBody), DISTANCE_MATRIX);
    }

    /**
     * This method is used to get geofence response.
     *
     * @param latitude  This latitude in double
     * @param longitude This longitude in double
     * @param listener  This is callback we use to return response
     */
    public void geofence(double latitude, double longitude, ResponseListener<GeofenceResponse> listener) {
        execute(listener, geofenceUrl(latitude, longitude), GEOFENCE);
    }

    /**
     * This method is used to get estimated time arrival response.
     *
     * @param requestBody This is object of type {@link EstimatedTimeArrivalRequest}
     * @param listener    This is callback we use to return response
     */
    public void estimatedTimeArrival(EstimatedTimeArrivalRequest requestBody, ResponseListener<EstimatedTimeArrivalResponse> listener) {
        execute(listener, estimatedTimeArrivalUrl(requestBody), ETA);
    }

    private void execute(final ResponseListener listener, HttpUrl.Builder urlBuilder, final String api) {
        client.newCall(
                new Request.Builder()
                        .url(urlBuilder.build().toString())
                        .addHeader("x-api-key", MapirService.getApiKey())
                        .addHeader("MapIr-SDK", MapirService.getUserAgent())
                        .get()
                        .build()
        ).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                call.cancel();

                new Handler(Looper.getMainLooper()).post(
                        new Runnable() {
                            @Override
                            public void run() {
                                listener.onError(new MapirError("Client Connection Error", 1000));
                            }
                        }
                );
            }

            @Override
            public void onResponse(Call call, final Response response) {
                new Handler(Looper.getMainLooper()).post(
                        new Runnable() {
                            @Override
                            public void run() {
                                if (!response.isSuccessful())
                                    listener.onError(createError(response.code(), api));
                                else {
                                    try {
                                        MapirResponse tempResponse = createResponse(response.body(), api);

                                        if (tempResponse instanceof StaticMapResponse) {
                                            new BitmapWorkerTask(listener).execute(((StaticMapResponse) tempResponse).getData());
                                        } else
                                            listener.onSuccess(tempResponse);
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }
                );
            }
        });
    }

    public static Geom createGeom(String geom) {
        try {
            JSONObject tempGeom = new JSONObject(geom);
            JSONArray tempCoordinates = new JSONArray(tempGeom.get("coordinates").toString());

            return new Geom(tempGeom.getString("type"), new Double[]{
                    tempCoordinates.getDouble(0),
                    tempCoordinates.getDouble(1)
            });
        } catch (Exception ignored) {
        }

        return null;
    }
    //endregion Methods
}
