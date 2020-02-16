package ir.map.servicesdk.app_kotlin

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import ir.map.servicesdk.MapService
import ir.map.servicesdk.ResponseListener
import ir.map.servicesdk.enums.*
import ir.map.servicesdk.model.base.MapirError
import ir.map.servicesdk.model.inner.DistanceMatrixPointRequest
import ir.map.servicesdk.request.DistanceMatrixRequest
import ir.map.servicesdk.request.EstimatedTimeArrivalRequest
import ir.map.servicesdk.request.RouteRequest
import ir.map.servicesdk.request.SearchRequest
import ir.map.servicesdk.response.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private val mapService = MapService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<View>(R.id.reverse_geo_code).setOnClickListener { reverseGeoCode() }
        findViewById<View>(R.id.fast_reverse_geo_code).setOnClickListener { fastReverseGeoCode() }
        findViewById<View>(R.id.plaque_reverse_geo_code).setOnClickListener { plaqueReverseGeoCode() }
        findViewById<View>(R.id.search).setOnClickListener { search() }
        findViewById<View>(R.id.autocomplete_search).setOnClickListener { autoCompleteSearch() }
        findViewById<View>(R.id.geo_fence).setOnClickListener { geofence() }
        findViewById<View>(R.id.static_map).setOnClickListener { staticMap() }
        findViewById<View>(R.id.distance_matrix).setOnClickListener { distanceMatrix() }
        findViewById<View>(R.id.route).setOnClickListener { route() }
        findViewById<View>(R.id.estimated_time_arrival).setOnClickListener { estimatedTimeArrival() }
    }

    private fun reverseGeoCode() {
        mapService.reverseGeoCode(35.1213654, 51.236548, object : ResponseListener<ReverseGeoCodeResponse> {
            override fun onSuccess(response: ReverseGeoCodeResponse) {
                Toast.makeText(this@MainActivity, "پاسخ آدرس یابی دریافت شد", Toast.LENGTH_SHORT).show()
            }

            override fun onError(error: MapirError) {
                Toast.makeText(this@MainActivity, "مشکلی در آدرس یابی پیش آمده", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun fastReverseGeoCode() {
        mapService.reverseGeoCode(35.1213654, 51.236548, object : ResponseListener<ReverseGeoCodeResponse> {
            override fun onSuccess(response: ReverseGeoCodeResponse) {
                Toast.makeText(this@MainActivity, "پاسخ آدرس یابی سریع دریافت شد", Toast.LENGTH_SHORT).show()
            }

            override fun onError(error: MapirError) {
                Toast.makeText(this@MainActivity, "مشکلی در آدرس یابی سریع پیش آمده", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun plaqueReverseGeoCode() {
        mapService.reverseGeoCode(35.1213654, 51.236548, object : ResponseListener<ReverseGeoCodeResponse> {
            override fun onSuccess(response: ReverseGeoCodeResponse) {
                Toast.makeText(this@MainActivity, "پاسخ آدرس یابی پلاک دار دریافت شد", Toast.LENGTH_SHORT).show()
            }

            override fun onError(error: MapirError) {
                Toast.makeText(this@MainActivity, "مشکلی در آدرس یابی پلاک دار پیش آمده", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun search() {
        mapService.search(
                SearchRequest.Companion.Builder("خیابان انقلاب")
                        .select(SelectOptions.POI)
                        .select(SelectOptions.ROADS)
                        .filter(FilterOptions.DISTANCE, 2000, DistanceUnit.METER)
                        .location(35.701499, 51.419921)
                        .build(),
                object : ResponseListener<SearchResponse> {
                    override fun onSuccess(response: SearchResponse) {
                        Toast.makeText(this@MainActivity, "پاسخ جستجو دریافت شد", Toast.LENGTH_SHORT).show()
                    }

                    override fun onError(error: MapirError) {
                        Toast.makeText(this@MainActivity, "مشکلی در جستجو پیش آمده", Toast.LENGTH_SHORT).show()
                    }
                }
        )
    }

    private fun autoCompleteSearch() {
        mapService.autoCompleteSearch(
                SearchRequest.Companion.Builder("بان قلب")
                        .select(SelectOptions.POI)
                        .select(SelectOptions.ROADS)
                        .filter(FilterOptions.PROVINCE, "تهران")
                        .build(),
                object : ResponseListener<AutoCompleteSearchResponse> {
                    override fun onSuccess(response: AutoCompleteSearchResponse) {
                        Toast.makeText(this@MainActivity, "پاسخ جستجوی سریع دریافت شد", Toast.LENGTH_SHORT).show()
                    }

                    override fun onError(error: MapirError) {
                        Toast.makeText(this@MainActivity, "مشکلی در جستجوی سریع پیش آمده", Toast.LENGTH_SHORT).show()
                    }
                }
        )
    }

    private fun geofence() {
        mapService.geofence(35.72986153843338, 51.40631675720215, object : ResponseListener<GeofenceResponse> {
            override fun onSuccess(response: GeofenceResponse) {
                Toast.makeText(this@MainActivity, "پاسخ حصار جغرافیایی دریافت شد", Toast.LENGTH_SHORT).show()
            }

            override fun onError(error: MapirError) {
                Toast.makeText(this@MainActivity, "مشکلی در حصار جغرافیایی پیش آمده", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun staticMap() {
        mapService.staticMap(35.808208, 51.507911, 800, 1200, 18, "2", StaticMapMarker.PINK, object : ResponseListener<StaticMapResponse> {
            override fun onSuccess(response: StaticMapResponse) {
                // ((ImageView) findViewById(R.id.sample_img)).setImageBitmap(response.getBitmapStaticMap())
                Toast.makeText(this@MainActivity, "پاسخ کروکی نقشه دریافت شد", Toast.LENGTH_SHORT).show()
            }

            override fun onError(error: MapirError) {
                Toast.makeText(this@MainActivity, "مشکلی در کروکی نقشه پیش آمده", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun distanceMatrix() {
        val tempOrigins: MutableList<DistanceMatrixPointRequest> = ArrayList()
        tempOrigins.add(DistanceMatrixPointRequest(1, 35.808208, 51.507911))
        tempOrigins.add(DistanceMatrixPointRequest(2, 35.808207, 51.500098))
        tempOrigins.add(DistanceMatrixPointRequest(3, 35.804201, 51.461849))
        tempOrigins.add(DistanceMatrixPointRequest(4, 35.780042, 51.414385))

        val tempDestinations: MutableList<DistanceMatrixPointRequest> = ArrayList()
        tempDestinations.add(DistanceMatrixPointRequest(5, 35.677769, 51.266842))
        tempDestinations.add(DistanceMatrixPointRequest(6, 35.643731, 51.383057))
        tempDestinations.add(DistanceMatrixPointRequest(7, 35.648619, 51.479530))
        tempDestinations.add(DistanceMatrixPointRequest(8, 35.676652, 51.373959))

        mapService.distanceMatrix(
                DistanceMatrixRequest.Companion.Builder(tempOrigins, tempDestinations)
                        .sorted(false)
                        .filter(DistanceMatrixOutputType.DISTANCE)
                        .build(),
                object : ResponseListener<DistanceMatrixResponse> {
                    override fun onSuccess(response: DistanceMatrixResponse) {
                        Toast.makeText(this@MainActivity, "پاسخ ماتریس فاصله دریافت شد", Toast.LENGTH_SHORT).show()
                    }

                    override fun onError(error: MapirError) {
                        Toast.makeText(this@MainActivity, "مشکلی در ماتریس فاصله پیش آمده", Toast.LENGTH_SHORT).show()
                    }
                }
        )
    }

    private fun route() {
        val requestBody: RouteRequest = RouteRequest.Companion.Builder(
                35.740312, 51.422625,
                35.722580, 51.451678,
                RouteType.DRIVING
        ).alternative(true)
                .addDestination(35.734826, 51.311946)
                .addDestination(35.699686, 51.337523)
                .build()

        mapService.route(requestBody, object : ResponseListener<RouteResponse> {
            override fun onSuccess(response: RouteResponse) {
                Toast.makeText(this@MainActivity, "پاسخ مسیریابی دریافت شد", Toast.LENGTH_SHORT).show()
            }

            override fun onError(error: MapirError) {
                Toast.makeText(this@MainActivity, "مشکلی در مسیریابی پیش آمده", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun estimatedTimeArrival() {
        mapService.estimatedTimeArrival(
                EstimatedTimeArrivalRequest.Companion.Builder(35.808208, 51.507911)
                        .addDestination(35.808207, 51.500098)
                        .addDestination(35.804201, 51.461849)
                        .addDestination(35.780042, 51.414385)
                        .addDestination(35.677769, 51.266842)
                        .addDestination(35.643731, 51.383057)
                        .addDestination(35.648619, 51.479530)
                        .addDestination(35.676652, 51.373959)
                        .build(),
                object : ResponseListener<EstimatedTimeArrivalResponse> {
                    override fun onSuccess(response: EstimatedTimeArrivalResponse) {
                        Toast.makeText(this@MainActivity, "پاسخ تخمین زمان رسیدن دریافت شد", Toast.LENGTH_SHORT).show()
                    }

                    override fun onError(error: MapirError) {
                        Toast.makeText(this@MainActivity, "مشکلی در تخمین زمان رسیدن پیش آمده", Toast.LENGTH_SHORT).show()
                    }
                })
    }
}
