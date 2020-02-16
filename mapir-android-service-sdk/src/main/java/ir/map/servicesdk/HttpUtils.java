package ir.map.servicesdk;

import java.io.IOException;

import ir.map.servicesdk.model.base.MapirError;
import ir.map.servicesdk.model.base.MapirResponse;
import okhttp3.ResponseBody;

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
import static ir.map.servicesdk.response.AutoCompleteSearchResponse.createAutoCompleteSearchResponse;
import static ir.map.servicesdk.response.DistanceMatrixResponse.createDistanceMatrixResponse;
import static ir.map.servicesdk.response.EstimatedTimeArrivalResponse.createEstimatedTimeArrivalResponse;
import static ir.map.servicesdk.response.FastReverseGeoCodeResponse.createFastReverseGeoCodeResponse;
import static ir.map.servicesdk.response.GeofenceResponse.createGeofenceResponse;
import static ir.map.servicesdk.response.PlaqueReverseGeoCodeResponse.createPlaqueReverseGeoCodeResponse;
import static ir.map.servicesdk.response.ReverseGeoCodeResponse.createReverseGeoCodeResponse;
import static ir.map.servicesdk.response.RouteResponse.createRouteResponse;
import static ir.map.servicesdk.response.SearchResponse.createSearchResponse;
import static ir.map.servicesdk.response.StaticMapResponse.createStaticMapResponse;

class HttpUtils {
    static MapirResponse createResponse(ResponseBody body, String api) throws IOException {
        switch (api) {
            case REVERSE:
                return createReverseGeoCodeResponse(body.string());
            case FAST_REVERSE:
                return createFastReverseGeoCodeResponse(body.string());
            case PLAQUE_REVERSE:
                return createPlaqueReverseGeoCodeResponse(body.string());
            case SEARCH:
                return createSearchResponse(body.string());
            case AUTOCOMPLETE_SEARCH:
                return createAutoCompleteSearchResponse(body.string());
            case ROUTE:
                return createRouteResponse(body.string());
            case STATIC_MAP:
                return createStaticMapResponse(body.byteStream());
            case DISTANCE_MATRIX:
                return createDistanceMatrixResponse(body.string());
            case GEOFENCE:
                return createGeofenceResponse(body.string());
            case ETA:
                return createEstimatedTimeArrivalResponse(body.string());
        }

        return null;
    }

    static MapirError createError(int statusCode, String api) {
        if (statusCode == 400)
            return new MapirError(api + " api: bad request.", 400);
        else if (statusCode == 401)
            return new MapirError(api + " api: get valid apiKey from https://corp.map.ir", 401);
        else if (statusCode == 402)
            return new MapirError(api + " api: bad request.", 402);
        else if (statusCode == 404)
            return new MapirError(api + " api: not found response.", 404);

        return null;
    }
}
