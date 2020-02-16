package ir.map.servicesdk.response

import ir.map.servicesdk.MapService.Companion.createGeom
import ir.map.servicesdk.model.base.MapirResponse
import ir.map.servicesdk.model.inner.SearchItem
import org.json.JSONArray
import org.json.JSONObject
import java.util.*

class AutoCompleteSearchResponse private constructor(val count: Int, val searchItems: List<SearchItem>) : MapirResponse() {
    companion object {
        fun createAutoCompleteSearchResponse(data: String?) = try {
            val tempData = JSONObject(data!!)
            val tempSearchItems = JSONArray(tempData["value"].toString())
            val searchItems: MutableList<SearchItem> = ArrayList<SearchItem>()
            for (i in 0 until tempSearchItems.length()) {
                val tempSearchItem = JSONObject(tempSearchItems[i].toString())
                val item: SearchItem = SearchItem(
                        tempSearchItem.getString("province"),
                        tempSearchItem.getString("county"),
                        tempSearchItem.getString("district"),
                        tempSearchItem.getString("city"),
                        tempSearchItem.getString("region"),
                        tempSearchItem.getString("neighborhood"),
                        tempSearchItem.getString("title"),
                        tempSearchItem.getString("address"),
                        tempSearchItem.getString("type"),
                        tempSearchItem.getString("fclass"),
                        createGeom(tempSearchItem["geom"].toString())!!
                )
                searchItems.add(item)
            }
            AutoCompleteSearchResponse(
                    tempData.getInt("odata.count"),
                    searchItems
            )
        } catch (e: Exception) {
            null
        }
    }
}