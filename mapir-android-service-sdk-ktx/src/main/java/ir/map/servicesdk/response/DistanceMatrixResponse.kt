package ir.map.servicesdk.response

import ir.map.servicesdk.model.base.MapirResponse
import ir.map.servicesdk.model.inner.DistanceMatrixPointResponse
import ir.map.servicesdk.model.inner.ResultByDistance
import ir.map.servicesdk.model.inner.ResultByDuration
import org.json.JSONArray
import org.json.JSONObject
import java.util.*

class DistanceMatrixResponse private constructor(
        val resultByDistance: List<ResultByDistance>,
        val resultByDuration: List<ResultByDuration>,
        val origins: List<DistanceMatrixPointResponse>,
        val destinations: List<DistanceMatrixPointResponse>
) : MapirResponse() {
    companion object {
        fun createDistanceMatrixResponse(data: String) = try {
            val tempData = JSONObject(data)
            val resultByDistanceItems: MutableList<ResultByDistance> = ArrayList()
            if (tempData.has("distance")) {
                val tempDistanceMatrixResultByDistanceItems = JSONArray(tempData["distance"].toString())
                for (i in 0 until tempDistanceMatrixResultByDistanceItems.length()) {
                    val tempResultItem = JSONObject(tempDistanceMatrixResultByDistanceItems[i].toString())
                    val item = ResultByDistance(
                            tempResultItem.getString("origin_index"),
                            tempResultItem.getString("destination_index"),
                            tempResultItem.getDouble("distance")
                    )
                    resultByDistanceItems.add(item)
                }
            }
            val resultByDurationItems: MutableList<ResultByDuration> = ArrayList()
            if (tempData.has("duration")) {
                val tempDistanceMatrixResultByDurationItems = JSONArray(tempData["duration"].toString())
                for (i in 0 until tempDistanceMatrixResultByDurationItems.length()) {
                    val tempResultItem = JSONObject(tempDistanceMatrixResultByDurationItems[i].toString())
                    val item = ResultByDuration(
                            tempResultItem.getString("origin_index"),
                            tempResultItem.getString("destination_index"),
                            tempResultItem.getDouble("duration")
                    )
                    resultByDurationItems.add(item)
                }
            }
            val tempDistanceMatrixOriginItems = JSONObject(tempData["origins"].toString())
            val tempDistanceMatrixOriginKeys = tempDistanceMatrixOriginItems.keys()
            val originsItems: MutableList<DistanceMatrixPointResponse> = ArrayList()
            while (tempDistanceMatrixOriginKeys.hasNext()) {
                val id = tempDistanceMatrixOriginKeys.next()
                val tempOriginItem = tempDistanceMatrixOriginItems.getJSONObject(id)
                val item = DistanceMatrixPointResponse(
                        id,
                        tempOriginItem.getString("name"),
                        tempOriginItem.getString("province_name"),
                        tempOriginItem.getString("county_name"),
                        tempOriginItem.getString("district_title"),
                        tempOriginItem.getString("ruraldistrict_title"),
                        tempOriginItem.getString("suburb_title"),
                        tempOriginItem.getString("neighbourhood_title")
                )
                originsItems.add(item)
            }
            val tempDistanceMatrixDestinationItems = JSONObject(tempData["destinations"].toString())
            val tempDistanceMatrixDestinationKeys = tempDistanceMatrixDestinationItems.keys()
            val destinationsItems: MutableList<DistanceMatrixPointResponse> = ArrayList()
            while (tempDistanceMatrixDestinationKeys.hasNext()) {
                val id = tempDistanceMatrixDestinationKeys.next()
                val tempDestinationItem = tempDistanceMatrixDestinationItems.getJSONObject(id)
                val item = DistanceMatrixPointResponse(
                        id,
                        tempDestinationItem.getString("name"),
                        tempDestinationItem.getString("province_name"),
                        tempDestinationItem.getString("county_name"),
                        tempDestinationItem.getString("district_title"),
                        tempDestinationItem.getString("ruraldistrict_title"),
                        tempDestinationItem.getString("suburb_title"),
                        tempDestinationItem.getString("neighbourhood_title")
                )
                destinationsItems.add(item)
            }
            DistanceMatrixResponse(
                    resultByDistanceItems,
                    resultByDurationItems,
                    originsItems,
                    destinationsItems
            )
        } catch (e: Exception) {
            null
        }
    }
}