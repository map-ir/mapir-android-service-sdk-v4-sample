package ir.map.servicesdk;

import ir.map.servicesdk.request.DistanceMatrixRequest;
import ir.map.servicesdk.request.EstimatedTimeArrivalRequest;
import ir.map.servicesdk.request.RouteRequest;
import ir.map.servicesdk.request.SearchRequest;
import ir.map.servicesdk.request.StaticMapRequest;
import okhttp3.HttpUrl;

class UrlBuilder {

    //region const
    private static String BASE_URL = "https://map.ir/";

    static final String REVERSE = "reverse";
    static final String FAST_REVERSE = "fast-reverse";
    static final String PLAQUE_REVERSE = "reverse/no";
    static final String SEARCH = "search/v2";
    static final String AUTOCOMPLETE_SEARCH = "search/v2/autocomplete";
    static final String ROUTE = "routes/";
    static final String STATIC_MAP = "static";
    static final String DISTANCE_MATRIX = "distancematrix";
    static final String GEOFENCE = "geofence/boundaries";
    static final String ETA = "eta/driving/";
    //endregion const

    //region methods
    static HttpUrl.Builder reverseGeoCodeUrl(String endpoint, double latitude, double longitude) {
        return HttpUrl.parse(BASE_URL + endpoint)
                .newBuilder()
                .addQueryParameter("lat", String.valueOf(latitude))
                .addQueryParameter("lon", String.valueOf(longitude));
    }

    static HttpUrl.Builder searchUrl(String endpoint, SearchRequest requestBody) {
        HttpUrl.Builder urlBuilder = HttpUrl.parse(BASE_URL + endpoint)
                .newBuilder()
                .addQueryParameter("text", requestBody.getText());

        if (requestBody.getSelects() == null && requestBody.getFilter() != null) {
            urlBuilder.addQueryParameter("$filter", requestBody.getFilter());

            if (requestBody.hasLatLng())
                urlBuilder.addQueryParameter("lat", String.valueOf(requestBody.getLatitude()))
                        .addQueryParameter("lon", String.valueOf(requestBody.getLongitude()));

        } else if (requestBody.getSelects() != null && requestBody.getFilter() == null) {
            urlBuilder.addQueryParameter("$select", requestBody.getSelects());

            if (requestBody.hasLatLng())
                urlBuilder.addQueryParameter("lat", String.valueOf(requestBody.getLatitude()))
                        .addQueryParameter("lon", String.valueOf(requestBody.getLongitude()));
        } else if (requestBody.getSelects() != null && requestBody.getFilter() != null) {
            urlBuilder.addQueryParameter("$select", requestBody.getSelects())
                    .addQueryParameter("$filter", requestBody.getFilter());

            if (requestBody.hasLatLng())
                urlBuilder.addQueryParameter("lat", String.valueOf(requestBody.getLatitude()))
                        .addQueryParameter("lon", String.valueOf(requestBody.getLongitude()));
        }

        return urlBuilder;
    }

    static HttpUrl.Builder routeUrl(RouteRequest requestBody) {
        String routeTypeEndPoint;

        if (requestBody.getRouteType().equals("route"))
            routeTypeEndPoint = (requestBody.hasRoutePlan() ? requestBody.getRoutePlan() : requestBody.getRouteType());
        else
            routeTypeEndPoint = requestBody.getRouteType();

        StringBuilder otherDestinations = new StringBuilder();

        if (requestBody.hasOtherDestinations()) {
            for (int i = 0; i < requestBody.getOtherDestinations().size(); i++) {
                otherDestinations
                        .append(";")
                        .append(requestBody.getOtherDestinations().get(i).getLongitude())
                        .append(",")
                        .append(requestBody.getOtherDestinations().get(i).getLatitude());
            }
        }

        HttpUrl.Builder urlBuilder = HttpUrl.parse(BASE_URL
                + ROUTE
                + routeTypeEndPoint
                + "/v1/driving"
                + "/" + requestBody.getStartLongitude()
                + "," + requestBody.getStartLatitude()
                + ";" + requestBody.getEndLongitude()
                + "," + requestBody.getEndLatitude()
                + otherDestinations.toString()
        )
                .newBuilder()
                .addQueryParameter("alternatives", String.valueOf(requestBody.isAlternatives()))
                .addQueryParameter("steps", String.valueOf(requestBody.needSteps()));

        if (requestBody.hasRouteOverView())
            urlBuilder.addQueryParameter("overview", requestBody.getRouteOverView());

        return urlBuilder;
    }

    static HttpUrl.Builder staticMapUrl(StaticMapRequest requestBody) {
        return HttpUrl.parse(BASE_URL + STATIC_MAP)
                .newBuilder()
                .addQueryParameter("width", String.valueOf(requestBody.getWidth()))
                .addQueryParameter("height", String.valueOf(requestBody.getHeight()))
                .addQueryParameter("zoom_level", String.valueOf(requestBody.getZoom()))
                .addEncodedQueryParameter("markers",
                        "color:" + requestBody.getColor() +
                                "%7Clabel:" + requestBody.getLabel() +
                                "%7C" + requestBody.getLongitude() +
                                "," + requestBody.getLatitude()
                );
    }

    static HttpUrl.Builder distanceMatrixUrl(DistanceMatrixRequest requestBody) {
        HttpUrl.Builder urlBuilder = HttpUrl.parse(BASE_URL + DISTANCE_MATRIX)
                .newBuilder()
                .addEncodedQueryParameter("origins", requestBody.getOrigins())
                .addEncodedQueryParameter("destinations", requestBody.getDestinations())
                .addQueryParameter("sorted", String.valueOf(requestBody.isSorted()));

        if (requestBody.hasFilter())
            urlBuilder.addQueryParameter("$filter", requestBody.getFilter());

        return urlBuilder;
    }

    static HttpUrl.Builder geofenceUrl(double latitude, double longitude) {
        return HttpUrl.parse(BASE_URL + GEOFENCE)
                .newBuilder()
                .addQueryParameter("lat", String.valueOf(latitude))
                .addQueryParameter("lon", String.valueOf(longitude));
    }

    static HttpUrl.Builder estimatedTimeArrivalUrl(EstimatedTimeArrivalRequest requestBody) {
        return HttpUrl.parse(BASE_URL + ETA + requestBody.getCoordinates()).newBuilder();
    }
    //endregion methods
}
