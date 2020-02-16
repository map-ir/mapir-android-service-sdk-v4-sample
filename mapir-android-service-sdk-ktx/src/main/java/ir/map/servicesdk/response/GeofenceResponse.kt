package ir.map.servicesdk.response

import ir.map.servicesdk.model.base.MapirResponse
import ir.map.servicesdk.model.inner.Boundry
import ir.map.servicesdk.model.inner.Coordinate
import ir.map.servicesdk.model.inner.GeofenceData
import org.json.JSONArray
import org.json.JSONObject
import java.util.*

class GeofenceResponse private constructor(val count: Int, val geofenceData: List<GeofenceData>) : MapirResponse() {
    companion object {
        fun createGeofenceResponse(data: String?) = try {
            val tempData = JSONObject(data!!)
            val tempGeofenceData = JSONArray(tempData["value"].toString())
            val geofenceData: MutableList<GeofenceData> = ArrayList()
            for (i in 0 until tempGeofenceData.length()) {
                val tempGeofenceItem = JSONObject(tempGeofenceData[i].toString())
                //region boundry
                val tempBoundry = JSONObject(tempGeofenceItem["boundary"].toString())
                //region coordinates
                val tempCoordinates = JSONArray(tempBoundry["coordinates"].toString())
                val coordinateCollection: MutableList<List<Coordinate>> = ArrayList()
                for (m in 0 until tempCoordinates.length()) {
                    val tempList = tempCoordinates.getJSONArray(m)
                    val coordinateList: MutableList<Coordinate> = ArrayList()
                    for (s in 0 until tempList.length()) {
                        val coordinate = Coordinate(
                                tempList.getJSONArray(s).getDouble(1),
                                tempList.getJSONArray(s).getDouble(0)
                        )
                        coordinateList.add(coordinate)
                    }
                    coordinateCollection.add(coordinateList)
                }
                //endregion coordinates
                val boundry = Boundry(
                        tempBoundry.getString("type"),
                        coordinateCollection
                )
                //endregion boundry
                val item = GeofenceData(
                        tempGeofenceItem.getInt("id"),
                        boundry,
                        tempGeofenceItem.getString("meta"),
                        tempGeofenceItem.getString("created_at"),
                        tempGeofenceItem.getString("updated_at")
                )
                geofenceData.add(item)
            }
            GeofenceResponse(
                    tempData.getInt("odata.count"),
                    geofenceData
            )
        } catch (e: Exception) {
            null
        }
    }
}