package ir.map.servicesdk.request

import ir.map.servicesdk.enums.RouteOverView
import ir.map.servicesdk.enums.RoutePlan
import ir.map.servicesdk.enums.RouteType
import ir.map.servicesdk.model.base.BaseModel
import ir.map.servicesdk.model.inner.Coordinate
import java.util.*


class RouteRequest private constructor(
        val startLatitude: Double, val startLongitude: Double,
        val endLatitude: Double, val endLongitude: Double,
        val routeType: String, val routePlan: String?,
        val routeOverView: String,
        val isAlternatives: Boolean,
        val steps: Boolean,
        val destinations: List<Coordinate>) : BaseModel() {

    fun hasRouteOverView() = routeOverView != "false"

    fun needSteps() = steps

    fun hasRoutePlan() = routePlan != null

    fun hasDestinations() = destinations.isNotEmpty()

    companion object {
        class Builder(
                private val startLatitude: Double,
                private val startLongitude: Double,
                private val endLatitude: Double,
                private val endLongitude: Double,
                private val routeType: RouteType
        ) {
            private var routePlan: String? = null
            private var routeOverView: String = RouteOverView.NONE.toString()
            private var alternatives = false
            private var steps = false
            var destinations: MutableList<Coordinate> = ArrayList()

            fun routePlan(routePlan: RoutePlan): Builder {
                if (routePlan != null) {
                    if (routeType == RouteType.DRIVING)
                        this.routePlan = routePlan.toString()
                    else
                        throw RuntimeException("can't have RoutePlan with RouteType equals to BICYCLE or WALKING")
                } else
                    throw RuntimeException("routePlan can't be null.")

                return this
            }

            fun routeOverView(routeOverView: RouteOverView): Builder {
                if (routeOverView != null)
                    this.routeOverView = routeOverView.toString()
                else
                    throw RuntimeException("routeOverView can not be null.")

                return this
            }

            fun alternative(value: Boolean): Builder {
                alternatives = value

                return this
            }

            fun steps(value: Boolean): Builder {
                steps = value

                return this
            }

            fun addDestination(latitude: Double, longitude: Double): Builder {
                if (latitude != null && longitude != null)
                    destinations.add(Coordinate(latitude, longitude))
                else
                    throw RuntimeException("latitude and longitude can't be null.")

                return this
            }

            fun build() = RouteRequest(
                    startLatitude, startLongitude,
                    endLatitude, endLongitude,
                    routeType.toString(),
                    routePlan,
                    routeOverView,
                    alternatives,
                    steps,
                    destinations)
        }
    }
}