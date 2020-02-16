package ir.map.servicesdk.request

import ir.map.servicesdk.enums.DistanceUnit
import ir.map.servicesdk.enums.FilterOptions
import ir.map.servicesdk.enums.SelectOptions
import ir.map.servicesdk.model.base.BaseModel
import java.util.*

class SearchRequest private constructor(
        val text: String,
        val selects: List<SelectOptions>,
        val filter: String?,
        var latitude: Double,
        var longitude: Double
) : BaseModel() {

    fun hasLatLng() = latitude != 0.0 && longitude != 0.0

    fun getSelects(): String? {
        if (selects != null && selects.isNotEmpty()) {
            val tempSelects = StringBuilder()

            for (i in 0 until selects.size - 1) {
                tempSelects.append(selects[i].toString())
                tempSelects.append(",")
            }

            return tempSelects.toString() + selects[selects.size - 1]
        }

        return null
    }

    companion object {
        class Builder(private val text: String) {
            private val selectOptions: MutableList<SelectOptions> = ArrayList()
            private var filter: String? = null
            private var latitude = 0.0
            private var longitude = 0.0

            fun location(latitude: Double, longitude: Double): Builder {
                this.latitude = latitude
                this.longitude = longitude

                return this
            }

            fun select(selectOption: SelectOptions): Builder {
                if (selectOption != null) {
                    if (selectOptions.contains(selectOption))
                        throw RuntimeException("Select option in search api can not be redundant. you set selectOption = $selectOption many times.") else selectOptions.add(selectOption)
                } else
                    throw RuntimeException("Select option in search api can not be null.")

                return this
            }

            fun filter(filterOption: FilterOptions, value: String): Builder {
                if (filterOption != null) {
                    if (filter == null) {
                        if (value != null) {
                            when (filterOption) {
                                FilterOptions.PROVINCE, FilterOptions.CITY, FilterOptions.COUNTY, FilterOptions.REGION, FilterOptions.NEIGHBOURHOOD, FilterOptions.POLYGON -> filter = filterOption.toString() + " eq " + value
                                FilterOptions.DISTANCE -> throw RuntimeException("DISTANCE Filter value must be in meter or kilometer number format.")
                            }
                        } else throw RuntimeException("Filter value in search api can not be null.")
                    } else throw RuntimeException("Filter option in search api must get just one value, check your code; you set many value for filter parameter.")
                } else throw RuntimeException("Filter option in search api can not be null.")
                return this
            }

            fun filter(filterOption: FilterOptions, value: Int, unit: DistanceUnit): Builder {
                if (filterOption != null) {
                    if (filter == null) {
                        if (value != null) {
                            when (filterOption) {
                                FilterOptions.PROVINCE, FilterOptions.CITY, FilterOptions.COUNTY, FilterOptions.REGION, FilterOptions.NEIGHBOURHOOD, FilterOptions.POLYGON -> throw RuntimeException(filterOption.toString() + " Filter value must be in String format.")
                                FilterOptions.DISTANCE -> filter = "$filterOption eq $value$unit"
                            }
                        } else throw RuntimeException("Filter value in search api can not be null.")
                    } else throw RuntimeException("Filter option in search api must get just one value, check your code; you set many value for filter parameter.")
                } else throw RuntimeException("Filter option in search api can not be null.")
                return this
            }

            fun build(): SearchRequest {
                if (filter == null && selectOptions.isEmpty()) {
                    latitude = 0.0
                    longitude = 0.0
                }

                if (filter != null && filter!!.contains(FilterOptions.DISTANCE.toString()) &&
                        latitude == 0.0 && longitude == 0.0)
                    throw RuntimeException("DISTANCE Filter option in search api needs set lat/lon.")

                if (selectOptions.contains(SelectOptions.NEARBY) &&
                        latitude == 0.0 &&
                        longitude == 0.0)
                    throw RuntimeException("NEARBY Select option in search api needs set lat/lon.")

                return SearchRequest(text, selectOptions, filter, latitude, longitude)
            }

        }
    }
}