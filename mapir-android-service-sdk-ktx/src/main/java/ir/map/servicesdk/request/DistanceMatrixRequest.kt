package ir.map.servicesdk.request

import ir.map.servicesdk.enums.DistanceMatrixOutputType
import ir.map.servicesdk.model.base.BaseModel
import ir.map.servicesdk.model.inner.DistanceMatrixPointRequest

class DistanceMatrixRequest private constructor(
        val origins: List<DistanceMatrixPointRequest>,
        val destinations: List<DistanceMatrixPointRequest>,
        val sorted: Boolean,
        val filter: String?) : BaseModel() {

    fun getOrigins(): String {
        val tempOrigins = StringBuilder()
        for (i in origins.indices) tempOrigins
                .append(origins[i].id)
                .append(",")
                .append(origins[i].latitude)
                .append(",")
                .append(origins[i].longitude)
                .append(if (i != origins.size - 1) "%7C" else "")

        return tempOrigins.toString()
    }

    fun getDestinations(): String {
        val tempDestinations = StringBuilder()
        for (i in destinations.indices) tempDestinations
                .append(destinations[i].id)
                .append(",")
                .append(destinations[i].latitude)
                .append(",")
                .append(destinations[i].longitude)
                .append(if (i != destinations.size - 1) "%7C" else "")

        return tempDestinations.toString()
    }

    fun hasFilter(): Boolean {
        return filter != null
    }

    companion object {
        class Builder(origins: List<DistanceMatrixPointRequest>, destinations: List<DistanceMatrixPointRequest>) {

            private val origins: List<DistanceMatrixPointRequest>
            private val destinations: List<DistanceMatrixPointRequest>
            private var sorted = false
            private var filter: String? = null

            fun sorted(value: Boolean): Builder {
                sorted = value
                return this
            }

            fun filter(outputType: DistanceMatrixOutputType): Builder {
                if (outputType != null) {
                    filter = "type eq $outputType"
                } else throw RuntimeException("Filter outputType can not be null.")

                return this
            }

            fun build(): DistanceMatrixRequest = DistanceMatrixRequest(origins, destinations, sorted, filter)

            init {
                if (origins.isEmpty())
                    throw RuntimeException("origins for distanceMatrix api must have at least on point.")
                else if (destinations.isEmpty())
                    throw RuntimeException("destinations for distanceMatrix api must have at least on point.")

                this.origins = origins
                this.destinations = destinations
            }
        }
    }
}