package ir.map.servicesdk.request

import ir.map.servicesdk.enums.StaticMapMarker
import ir.map.servicesdk.model.base.BaseModel

class StaticMapRequest(
        val latitude: Double,
        val longitude: Double,
        val width: Int,
        val height: Int,
        val zoom: Int = 1,
        val label: String,
        val color: StaticMapMarker
) : BaseModel() {
    init {
        if (zoom < 1 || zoom > 20)
            throw RuntimeException("Zomm level for static map api must be between 1 and 20; (can't be $zoom")
    }
}