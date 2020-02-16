package ir.map.servicesdk.model.inner

class DistanceMatrixPointRequest {
    var id: String
    var latitude: Double
    var longitude: Double

    constructor(id: String, latitude: Double, longitude: Double) {
        this.id = id
        this.latitude = latitude
        this.longitude = longitude
    }

    constructor(id: Int, latitude: Double, longitude: Double) {
        this.id = id.toString()
        this.latitude = latitude
        this.longitude = longitude
    }
}