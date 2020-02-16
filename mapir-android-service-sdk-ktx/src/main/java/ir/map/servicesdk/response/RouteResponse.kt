package ir.map.servicesdk.response

import ir.map.servicesdk.model.base.MapirResponse
import ir.map.servicesdk.model.inner.*
import org.json.JSONArray
import org.json.JSONObject
import java.util.*

class RouteResponse(val routes: List<RouteItem>, val wayPoints: List<WayPoint>) : MapirResponse() {
    companion object {
        fun createRouteResponse(data: String?) = try {
            val tempData = JSONObject(data!!)
            val tempRouteItems = JSONArray(tempData["routes"].toString())
            val routeItems: MutableList<RouteItem> = ArrayList()

            for (i in 0 until tempRouteItems.length()) {
                val tempRouteItem = JSONObject(tempRouteItems[i].toString())

                //region legs
                val tempLegs = JSONArray(tempRouteItem["legs"].toString())
                val legs: MutableList<Leg> = ArrayList()

                for (j in 0 until tempLegs.length()) {
                    val tempLeg = JSONObject(tempLegs[j].toString())

                    //region steps
                    val tempSteps = JSONArray(tempLeg["steps"].toString())
                    val steps: MutableList<Step> = ArrayList()

                    for (k in 0 until tempSteps.length()) {
                        val tempStep = JSONObject(tempSteps[k].toString())

                        //region intersection
                        val tempIntersections = JSONArray(tempStep["intersections"].toString())
                        val intersections: MutableList<Intersection> = ArrayList()

                        for (l in 0 until tempIntersections.length()) {
                            val tempIntersection = JSONObject(tempIntersections[l].toString())
                            val tempEntry = JSONArray(tempIntersection["entry"].toString())
                            val entries: MutableList<Boolean> = ArrayList()

                            for (m in 0 until tempEntry.length())
                                entries.add(tempEntry.getBoolean(m))

                            val tempBearing = JSONArray(tempIntersection["bearings"].toString())
                            val bearings: MutableList<Int> = ArrayList()

                            for (m in 0 until tempBearing.length())
                                bearings.add(tempBearing.getInt(m))

                            val tempLocation = JSONArray(tempIntersection["location"].toString())
                            val location: MutableList<Double> = ArrayList()

                            for (m in 0 until tempLocation.length())
                                location.add(tempLocation.getDouble(m))

                            var _in = -1
                            var out = -1

                            if (tempIntersection.has("in"))
                                _in = tempIntersection.getInt("in")

                            if (tempIntersection.has("out"))
                                out = tempIntersection.getInt("out")

                            val item = Intersection(
                                    _in,
                                    out,
                                    entries,
                                    bearings,
                                    location
                            )

                            intersections.add(item)
                        }
                        //endregion intersection

                        //region maneuver
                        val tempManeuver = JSONObject(tempStep.getString("maneuver"))
                        val tempLocationManeuver = JSONArray(tempManeuver.getString("location"))
                        val location: MutableList<Double> = ArrayList()

                        for (m in 0 until tempLocationManeuver.length())
                            location.add(tempLocationManeuver.getDouble(m))

                        var modifier: String? = null
                        if (tempManeuver.has("modifier"))
                            modifier = tempManeuver.getString("modifier")

                        val tempManeuverObject = Maneuver(
                                tempManeuver.getInt("bearing_after"),
                                location,
                                tempManeuver.getInt("bearing_before"),
                                tempManeuver.getString("type"),
                                modifier
                        )
                        //endregion maneuver

                        val item = Step(
                                intersections,
                                tempStep.getString("driving_side"),
                                tempStep.getString("geometry"),
                                tempStep.getString("mode"),
                                tempStep.getInt("duration"),
                                tempManeuverObject,
                                tempStep.getInt("weight"),
                                tempStep.getInt("distance"),
                                tempStep.getString("distance")
                        )

                        steps.add(item)
                    }
                    //endregion steps
                    val item = Leg(
                            tempLeg.getDouble("distance"),
                            tempLeg.getDouble("duration"),
                            tempLeg.getString("summary"),
                            tempLeg.getDouble("weight"),
                            steps
                    )

                    legs.add(item)
                }
                //endregion legs

                val item = RouteItem(
                        tempRouteItem.getString("geometry"),
                        tempRouteItem.getDouble("distance"),
                        tempRouteItem.getDouble("duration"),
                        tempRouteItem.getString("weight_name"),
                        tempRouteItem.getDouble("weight"),
                        legs
                )

                routeItems.add(item)
            }
            val tempWayPoints = JSONArray(tempData["waypoints"].toString())
            val wayPoints: MutableList<WayPoint> = ArrayList()

            for (i in 0 until tempWayPoints.length()) {
                val tempWayPoint = JSONObject(tempWayPoints[i].toString())
                val tempLocation = JSONArray(tempWayPoint.getString("location"))
                val item = WayPoint(
                        tempWayPoint.getString("hint"),
                        tempWayPoint.getDouble("distance"),
                        tempWayPoint.getString("name"),
                        tempLocation.getDouble(1),
                        tempLocation.getDouble(0)
                )

                wayPoints.add(item)
            }

            RouteResponse(
                    routeItems,
                    wayPoints
            )
        } catch (e: Exception) {
            null
        }
    }
}