package ir.map.servicesdk

import ir.map.servicesdk.model.base.MapirError
import ir.map.servicesdk.model.base.MapirResponse
import ir.map.servicesdk.response.*
import okhttp3.ResponseBody

internal object HttpUtils {
    fun createResponse(body: ResponseBody, api: String?): MapirResponse? {
        when (api) {
            UrlBuilder.REVERSE -> return ReverseGeoCodeResponse.createReverseGeoCodeResponse(body.string())
            UrlBuilder.FAST_REVERSE -> return FastReverseGeoCodeResponse.createFastReverseGeoCodeResponse(body.string())
            UrlBuilder.PLAQUE_REVERSE -> return PlaqueReverseGeoCodeResponse.createPlaqueReverseGeoCodeResponse(body.string())
            UrlBuilder.SEARCH -> return SearchResponse.createSearchResponse(body.string())
            UrlBuilder.AUTOCOMPLETE_SEARCH -> return AutoCompleteSearchResponse.createAutoCompleteSearchResponse(body.string())
            UrlBuilder.ROUTE -> return RouteResponse.createRouteResponse(body.string())
            UrlBuilder.STATIC_MAP -> return StaticMapResponse.createStaticMapResponse(body.byteStream())
            UrlBuilder.DISTANCE_MATRIX -> return DistanceMatrixResponse.createDistanceMatrixResponse(body.string())
            UrlBuilder.GEOFENCE -> return GeofenceResponse.createGeofenceResponse(body.string())
            UrlBuilder.ETA -> return EstimatedTimeArrivalResponse.createEstimatedTimeArrivalResponse(body.string())
        }

        return null
    }

    fun createError(statusCode: Int, api: String): MapirError {
        return when (statusCode) {
            400 -> MapirError("$api api: bad request.", statusCode)
            401 -> MapirError("$api api: get valid apiKey from https://corp.map.ir", statusCode)
            402 -> MapirError("$api api: bad request.", statusCode)
            404 -> MapirError("$api api: not found response.", statusCode)
            else -> MapirError("$api api: failed request.", statusCode)
        }
    }
}