package ir.map.servicesdk

import android.os.Handler
import android.os.Looper
import ir.map.servicesdk.UrlBuilder.AUTOCOMPLETE_SEARCH
import ir.map.servicesdk.UrlBuilder.DISTANCE_MATRIX
import ir.map.servicesdk.UrlBuilder.ETA
import ir.map.servicesdk.UrlBuilder.FAST_REVERSE
import ir.map.servicesdk.UrlBuilder.GEOFENCE
import ir.map.servicesdk.UrlBuilder.PLAQUE_REVERSE
import ir.map.servicesdk.UrlBuilder.REVERSE
import ir.map.servicesdk.UrlBuilder.ROUTE
import ir.map.servicesdk.UrlBuilder.SEARCH
import ir.map.servicesdk.UrlBuilder.STATIC_MAP
import ir.map.servicesdk.UrlBuilder.distanceMatrixUrl
import ir.map.servicesdk.UrlBuilder.estimatedTimeArrivalUrl
import ir.map.servicesdk.UrlBuilder.geofenceUrl
import ir.map.servicesdk.UrlBuilder.reverseGeoCodeUrl
import ir.map.servicesdk.UrlBuilder.routeUrl
import ir.map.servicesdk.UrlBuilder.searchUrl
import ir.map.servicesdk.UrlBuilder.staticMapUrl
import ir.map.servicesdk.enums.StaticMapMarker
import ir.map.servicesdk.model.base.BaseInterface
import ir.map.servicesdk.model.base.MapirError
import ir.map.servicesdk.model.base.MapirResponse
import ir.map.servicesdk.model.inner.Geom
import ir.map.servicesdk.request.*
import ir.map.servicesdk.response.*
import okhttp3.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException

/**
 * MapService prepares map.ir services
 *
 * @author Morteza Hosseini
 * @version 4.0.0
 * @since 2020-01-15
 */
class MapService {

    //region Initialize
    private val client = OkHttpClient()
    //endregion Initialize

    //region Methods
    /**
     * This method is used to get reverse geocode response.
     *
     * @param latitude  This latitude in double
     * @param longitude This longitude in double
     * @param listener  This is callback we use to return response
     */
    fun reverseGeoCode(latitude: Double, longitude: Double, listener: ResponseListener<ReverseGeoCodeResponse>) {
        execute(listener, reverseGeoCodeUrl(REVERSE, latitude, longitude), REVERSE)
    }

    /**
     * This method is used to get fast reverse geocode response.
     *
     * @param latitude  This latitude in double
     * @param longitude This longitude in double
     * @param listener  This is callback we use to return response
     */
    fun fastReverseGeoCode(latitude: Double, longitude: Double, listener: ResponseListener<FastReverseGeoCodeResponse>) {
        execute(listener, reverseGeoCodeUrl(FAST_REVERSE, latitude, longitude), FAST_REVERSE)
    }

    /**
     * This method is used to get plaque reverse geocode response.
     *
     * @param latitude  This latitude in double
     * @param longitude This longitude in double
     * @param listener  This is callback we use to return response
     */
    fun plaqueReverseGeoCode(latitude: Double, longitude: Double, listener: ResponseListener<PlaqueReverseGeoCodeResponse>) {
        execute(listener, reverseGeoCodeUrl(PLAQUE_REVERSE, latitude, longitude), PLAQUE_REVERSE)
    }

    /**
     * This method is used to get search response.
     *
     * @param requestBody This is object of type SearchRequest
     * @param listener    This is callback we use to return response
     */
    fun search(requestBody: SearchRequest, listener: ResponseListener<SearchResponse>) {
        execute(listener, searchUrl(SEARCH, requestBody), SEARCH)
    }

    /**
     * This method is used to get autocomplete search response.
     *
     * @param requestBody This is object of type [SearchRequest]
     * @param listener    This is callback we use to return response
     */
    fun autoCompleteSearch(requestBody: SearchRequest, listener: ResponseListener<AutoCompleteSearchResponse>) {
        execute(listener, searchUrl(AUTOCOMPLETE_SEARCH, requestBody), AUTOCOMPLETE_SEARCH)
    }

    /**
     * This method is used to get route response.
     *
     * @param requestBody This is object of type [RouteRequest]
     * @param listener    This is callback we use to return response
     */
    fun route(requestBody: RouteRequest, listener: ResponseListener<RouteResponse>) {
        execute(listener, routeUrl(requestBody), ROUTE)
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
     * @param color     This is color or type of marker positioned on specific point: [StaticMapMarker]
     * @param listener  This is callback we use to return response
     */
    fun staticMap(latitude: Double, longitude: Double, width: Int, height: Int, zoom: Int, label: String, color: StaticMapMarker, listener: ResponseListener<StaticMapResponse>) {
        execute(listener, staticMapUrl(StaticMapRequest(latitude, longitude, width, height, zoom, label, color)), STATIC_MAP)
    }

    /**
     * This method is used to get distance matrix response.
     *
     * @param requestBody This is object of type [DistanceMatrixRequest]
     * @param listener    This is callback we use to return response
     */
    fun distanceMatrix(requestBody: DistanceMatrixRequest, listener: ResponseListener<DistanceMatrixResponse>) {
        execute(listener, distanceMatrixUrl(requestBody), DISTANCE_MATRIX)
    }

    /**
     * This method is used to get geofence response.
     *
     * @param latitude  This latitude in double
     * @param longitude This longitude in double
     * @param listener  This is callback we use to return response
     */
    fun geofence(latitude: Double, longitude: Double, listener: ResponseListener<GeofenceResponse>) {
        execute(listener, geofenceUrl(latitude, longitude), GEOFENCE)
    }

    /**
     * This method is used to get estimated time arrival response.
     *
     * @param requestBody This is object of type [EstimatedTimeArrivalRequest]
     * @param listener    This is callback we use to return response
     */
    fun estimatedTimeArrival(requestBody: EstimatedTimeArrivalRequest, listener: ResponseListener<EstimatedTimeArrivalResponse>) {
        execute(listener, estimatedTimeArrivalUrl(requestBody), ETA)
    }

    @Suppress("UNCHECKED_CAST")
    private fun execute(listener: BaseInterface, urlBuilder: HttpUrl.Builder, api: String) {
        client.newCall(
                Request.Builder()
                        .url(urlBuilder.build().toString())
                        .addHeader("x-api-key", MapirService.apiKey!!)
                        .addHeader("MapIr-SDK", MapirService.userAgent!!)
                        .get()
                        .build()
        ).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                call.cancel()
                Handler(Looper.getMainLooper()).post { (listener as ResponseListener<*>).onError(MapirError("Client Connection Error", 1000)) }
            }

            override fun onResponse(call: Call, response: Response) {
                Handler(Looper.getMainLooper()).post {
                    if (!response.isSuccessful) (listener as ResponseListener<*>).onError(HttpUtils.createError(response.code(), api)) else {
                        try {
                            val tempResponse: MapirResponse = response.body()?.let { HttpUtils.createResponse(it, api) }!!

                            if (tempResponse is StaticMapResponse) {
                                BitmapWorkerTask(listener as ResponseListener<StaticMapResponse>).execute(tempResponse.data)
                            } else (listener as ResponseListener<MapirResponse>).onSuccess(tempResponse)
                        } catch (e: IOException) {
                            e.printStackTrace()
                        }
                    }
                }
            }
        })
    }
    //endregion Methods

    companion object {
        fun createGeom(geom: String): Geom? {
            try {
                val tempGeom = JSONObject(geom)
                val tempCoordinates = JSONArray(tempGeom["coordinates"].toString())

                return Geom(tempGeom.getString("type"), arrayOf(
                        tempCoordinates.getDouble(0),
                        tempCoordinates.getDouble(1)
                ))
            } catch (ignored: Exception) {
            }

            return null
        }
    }
}