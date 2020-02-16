package ir.map.servicesdk.model.inner

class Geom(val type: String, val coordinates: Array<Double>) {

    val latitude: Double
        get() = coordinates[1]

    val longitude: Double
        get() = coordinates[0]
}