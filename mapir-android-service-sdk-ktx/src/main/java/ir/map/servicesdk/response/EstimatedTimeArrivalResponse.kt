package ir.map.servicesdk.response

import ir.map.servicesdk.model.base.MapirResponse
import ir.map.servicesdk.model.inner.ETALeg
import org.json.JSONArray
import org.json.JSONObject
import java.util.*

class EstimatedTimeArrivalResponse private constructor(val distance: Double, val duration: Double, val legs: List<ETALeg>) : MapirResponse() {
    companion object {
        fun createEstimatedTimeArrivalResponse(data: String?) = try {
            val tempData = JSONObject(data)
            val tempRoutes = JSONObject(tempData["routes"].toString())
            val tempLegs = JSONArray(tempRoutes["legs"].toString())
            val legItems: MutableList<ETALeg> = ArrayList<ETALeg>()
            for (i in 0 until tempLegs.length()) {
                val tempLeg = JSONObject(tempLegs[i].toString())
                legItems.add(
                        ETALeg(
                                tempLeg.getDouble("distance"),
                                tempLeg.getDouble("duration")
                        )
                )
            }
            EstimatedTimeArrivalResponse(
                    tempRoutes.getDouble("distance"),
                    tempRoutes.getDouble("duration"),
                    legItems
            )
        } catch (e: Exception) {
            null
        }
    }
}