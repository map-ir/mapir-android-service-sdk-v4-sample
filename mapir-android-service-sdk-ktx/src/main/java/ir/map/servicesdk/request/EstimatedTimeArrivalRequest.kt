package ir.map.servicesdk.request

import ir.map.servicesdk.model.base.BaseModel
import ir.map.servicesdk.model.inner.Coordinate
import java.util.*

class EstimatedTimeArrivalRequest(
        val originLatitude: Double,
        val originLongitude: Double,
        val destinations: List<Coordinate>) : BaseModel() {

    fun getDestination(index: Int): Coordinate? {
        return if (destinations.isEmpty()) null else destinations[index]
    }

    val coordinates: String
        get() {
            val coordinates = StringBuilder()

            coordinates.append(originLongitude).append(",").append(originLatitude)

            for (i in destinations.indices)
                coordinates
                        .append(";")
                        .append(destinations[i].longitude)
                        .append(",")
                        .append(destinations[i].latitude)

            return coordinates.toString()
        }

    companion object {
        class Builder(
                private val startLatitude: Double,
                private val startLongitude: Double
        ) {
            var destinations: MutableList<Coordinate> = ArrayList()

            fun addDestination(latitude: Double, longitude: Double): Builder {
                if (latitude != null && longitude != null)
                    destinations.add(Coordinate(latitude, longitude))
                else
                    throw RuntimeException("latitude and longitude can't be null.")

                return this
            }

            fun build(): EstimatedTimeArrivalRequest {
                if (destinations.isEmpty())
                    throw RuntimeException("ETA must have at least one destination.")

                return EstimatedTimeArrivalRequest(startLatitude, startLongitude, destinations)
            }
        }
    }
}