package ir.map.servicesdk.response

import ir.map.servicesdk.MapService.Companion.createGeom
import ir.map.servicesdk.model.base.MapirResponse
import ir.map.servicesdk.model.inner.Geom
import org.json.JSONObject

class PlaqueReverseGeoCodeResponse(
        val address: String,
        val postalAddress: String,
        val addressCompact: String,
        val last: String,
        val name: String,
        val poi: String,
        val country: String,
        val province: String,
        val county: String,
        val district: String,
        val ruralDistrict: String,
        val city: String,
        val village: String,
        val region: String,
        val neighbourhood: String,
        val primary: String,
        val plaque: String,
        val postalCode: String,
        val geom: Geom
) : MapirResponse() {
    companion object {
        fun createPlaqueReverseGeoCodeResponse(data: String?) = try {
            val tempData = JSONObject(data!!)
            PlaqueReverseGeoCodeResponse(
                    tempData.getString("address"),
                    tempData.getString("postal_address"),
                    tempData.getString("address_compact"),
                    tempData.getString("last"),
                    tempData.getString("name"),
                    tempData.getString("poi"),
                    tempData.getString("country"),
                    tempData.getString("province"),
                    tempData.getString("county"),
                    tempData.getString("district"),
                    tempData.getString("rural_district"),
                    tempData.getString("city"),
                    tempData.getString("village"),
                    tempData.getString("region"),
                    tempData.getString("neighbourhood"),
                    tempData.getString("primary"),
                    tempData.getString("plaque"),
                    tempData.getString("postal_code"),
                    createGeom(tempData["geom"].toString())!!
            )
        } catch (e: Exception) {
            null
        }
    }
}