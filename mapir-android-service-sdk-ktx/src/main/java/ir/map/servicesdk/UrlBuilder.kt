package ir.map.servicesdk

import ir.map.servicesdk.request.*
import okhttp3.HttpUrl

internal object UrlBuilder {

    //region const
    private const val BASE_URL = "https://map.ir/"
    const val REVERSE = "reverse"
    const val FAST_REVERSE = "fast-reverse"
    const val PLAQUE_REVERSE = "reverse/no"
    const val SEARCH = "search/v2"
    const val AUTOCOMPLETE_SEARCH = "search/v2/autocomplete"
    const val ROUTE = "routes/"
    const val STATIC_MAP = "static"
    const val DISTANCE_MATRIX = "distancematrix"
    const val GEOFENCE = "geofence/boundaries"
    const val ETA = "eta/driving/"
    //endregion const

    //region methods
    fun reverseGeoCodeUrl(endpoint: String, latitude: Double, longitude: Double): HttpUrl.Builder {
        return HttpUrl.parse(BASE_URL + endpoint)!!
                .newBuilder()
                .addQueryParameter("lat", latitude.toString())
                .addQueryParameter("lon", longitude.toString())
    }

    fun searchUrl(endpoint: String, requestBody: SearchRequest): HttpUrl.Builder {
        val urlBuilder = HttpUrl.parse(BASE_URL + endpoint)!!
                .newBuilder()
                .addQueryParameter("text", requestBody.text)
        if (requestBody.getSelects() == null && requestBody.filter != null) {
            urlBuilder.addQueryParameter("\$filter", requestBody.filter)
            if (requestBody.hasLatLng()) urlBuilder.addQueryParameter("lat", requestBody.latitude.toString())
                    .addQueryParameter("lon", requestBody.longitude.toString())
        } else if (requestBody.getSelects() != null && requestBody.filter == null) {
            urlBuilder.addQueryParameter("\$select", requestBody.getSelects())
            if (requestBody.hasLatLng()) urlBuilder.addQueryParameter("lat", requestBody.latitude.toString())
                    .addQueryParameter("lon", requestBody.longitude.toString())
        } else if (requestBody.getSelects() != null && requestBody.filter != null) {
            urlBuilder.addQueryParameter("\$select", requestBody.getSelects())
                    .addQueryParameter("\$filter", requestBody.filter)
            if (requestBody.hasLatLng()) urlBuilder.addQueryParameter("lat", requestBody.latitude.toString())
                    .addQueryParameter("lon", requestBody.longitude.toString())
        }
        return urlBuilder
    }

    fun routeUrl(requestBody: RouteRequest): HttpUrl.Builder {
        val routeTypeEndPoint =
                if (requestBody.routeType == "route")
                    if (requestBody.hasRoutePlan())
                        requestBody.routePlan
                    else
                        requestBody.routeType
                else
                    requestBody.routeType

        val otherDestinations = StringBuilder()
        if (requestBody.hasDestinations()) {
            for (i in requestBody.destinations.indices) {
                otherDestinations
                        .append(";")
                        .append(requestBody.destinations[i].longitude)
                        .append(",")
                        .append(requestBody.destinations[i].latitude)
            }
        }
        val urlBuilder = HttpUrl.parse(BASE_URL
                + ROUTE
                + routeTypeEndPoint
                + "/v1/driving"
                + "/" + requestBody.startLongitude
                + "," + requestBody.startLatitude
                + ";" + requestBody.endLongitude
                + "," + requestBody.endLatitude
                + otherDestinations.toString()
        )!!
                .newBuilder()
                .addQueryParameter("alternatives", requestBody.isAlternatives.toString())
                .addQueryParameter("steps", requestBody.needSteps().toString())
        if (requestBody.hasRouteOverView()) urlBuilder.addQueryParameter("overview", requestBody.routeOverView)
        return urlBuilder
    }

    fun staticMapUrl(requestBody: StaticMapRequest): HttpUrl.Builder {
        return HttpUrl.parse(BASE_URL + STATIC_MAP)!!
                .newBuilder()
                .addQueryParameter("width", requestBody.width.toString())
                .addQueryParameter("height", requestBody.height.toString())
                .addQueryParameter("zoom_level", requestBody.zoom.toString())
                .addEncodedQueryParameter("markers",
                        "color:" + requestBody.color +
                                "%7Clabel:" + requestBody.label +
                                "%7C" + requestBody.longitude +
                                "," + requestBody.latitude
                )
    }

    fun distanceMatrixUrl(requestBody: DistanceMatrixRequest): HttpUrl.Builder {
        val urlBuilder = HttpUrl.parse(BASE_URL + DISTANCE_MATRIX)!!
                .newBuilder()
                .addEncodedQueryParameter("origins", requestBody.getOrigins())
                .addEncodedQueryParameter("destinations", requestBody.getDestinations())
                .addQueryParameter("sorted", requestBody.sorted.toString())
        if (requestBody.hasFilter()) urlBuilder.addQueryParameter("\$filter", requestBody.filter)
        return urlBuilder
    }

    fun geofenceUrl(latitude: Double, longitude: Double): HttpUrl.Builder {
        return HttpUrl.parse(BASE_URL + GEOFENCE)!!
                .newBuilder()
                .addQueryParameter("lat", latitude.toString())
                .addQueryParameter("lon", longitude.toString())
    }

    fun estimatedTimeArrivalUrl(requestBody: EstimatedTimeArrivalRequest): HttpUrl.Builder {
        return HttpUrl.parse(BASE_URL + ETA + requestBody.coordinates)!!.newBuilder()
    }
    //endregion methods
}