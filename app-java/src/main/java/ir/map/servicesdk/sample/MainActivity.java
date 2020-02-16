package ir.map.servicesdk.sample;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import ir.map.servicesdk.MapService;
import ir.map.servicesdk.ResponseListener;
import ir.map.servicesdk.enums.DistanceMatrixOutputType;
import ir.map.servicesdk.enums.DistanceUnit;
import ir.map.servicesdk.enums.FilterOptions;
import ir.map.servicesdk.enums.RouteType;
import ir.map.servicesdk.enums.SelectOptions;
import ir.map.servicesdk.enums.StaticMapMarker;
import ir.map.servicesdk.model.base.MapirError;
import ir.map.servicesdk.model.inner.DistanceMatrixPointRequest;
import ir.map.servicesdk.request.DistanceMatrixRequest;
import ir.map.servicesdk.request.EstimatedTimeArrivalRequest;
import ir.map.servicesdk.request.RouteRequest;
import ir.map.servicesdk.request.SearchRequest;
import ir.map.servicesdk.response.AutoCompleteSearchResponse;
import ir.map.servicesdk.response.DistanceMatrixResponse;
import ir.map.servicesdk.response.EstimatedTimeArrivalResponse;
import ir.map.servicesdk.response.FastReverseGeoCodeResponse;
import ir.map.servicesdk.response.GeofenceResponse;
import ir.map.servicesdk.response.PlaqueReverseGeoCodeResponse;
import ir.map.servicesdk.response.ReverseGeoCodeResponse;
import ir.map.servicesdk.response.RouteResponse;
import ir.map.servicesdk.response.SearchResponse;
import ir.map.servicesdk.response.StaticMapResponse;

public class MainActivity extends AppCompatActivity {

    private MapService mapService = new MapService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.reverse_geo_code).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reverseGeoCode();
            }
        });

        findViewById(R.id.fast_reverse_geo_code).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fastReverseGeoCode();
            }
        });

        findViewById(R.id.plaque_reverse_geo_code).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                plaqueReverseGeoCode();
            }
        });

        findViewById(R.id.search).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search();
            }
        });

        findViewById(R.id.autocomplete_search).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                autoCompleteSearch();
            }
        });

        findViewById(R.id.geo_fence).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                geofence();
            }
        });

        findViewById(R.id.static_map).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                staticMap();
            }
        });

        findViewById(R.id.distance_matrix).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                distanceMatrix();
            }
        });

        findViewById(R.id.route).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                route();
            }
        });

        findViewById(R.id.estimated_time_arrival).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                estimatedTimeArrival();
            }
        });
    }

    private void reverseGeoCode() {
        mapService.reverseGeoCode(35.1213654, 51.236548, new ResponseListener<ReverseGeoCodeResponse>() {
            @Override
            public void onSuccess(ReverseGeoCodeResponse response) {
                Toast.makeText(MainActivity.this, "پاسخ آدرس یابی دریافت شد", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(MapirError error) {
                Toast.makeText(MainActivity.this, "مشکلی در آدرس یابی پیش آمده", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void fastReverseGeoCode() {
        mapService.fastReverseGeoCode(35.1213654, 51.236548, new ResponseListener<FastReverseGeoCodeResponse>() {
            @Override
            public void onSuccess(FastReverseGeoCodeResponse response) {
                Toast.makeText(MainActivity.this, "پاسخ آدرس یابی سریع دریافت شد", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(MapirError error) {
                Toast.makeText(MainActivity.this, "مشکلی در آدرس یابی سریع پیش آمده", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void plaqueReverseGeoCode() {
        mapService.plaqueReverseGeoCode(35.1213654, 51.236548, new ResponseListener<PlaqueReverseGeoCodeResponse>() {
            @Override
            public void onSuccess(PlaqueReverseGeoCodeResponse response) {
                Toast.makeText(MainActivity.this, "پاسخ آدرس یابی پلاک دار دریافت شد", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(MapirError error) {
                Toast.makeText(MainActivity.this, "مشکلی در آدرس یابی پلاک دار پیش آمده", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void search() {
        mapService.search(
                new SearchRequest.Builder("خیابان انقلاب")
                        .select(SelectOptions.POI)
                        .select(SelectOptions.ROADS)
                        .filter(FilterOptions.DISTANCE, 2000, DistanceUnit.METER)
                        .location(35.701499, 51.419921)
                        .build(),
                new ResponseListener<SearchResponse>() {
                    @Override
                    public void onSuccess(SearchResponse response) {
                        Toast.makeText(MainActivity.this, "پاسخ جستجو دریافت شد", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(MapirError error) {
                        Toast.makeText(MainActivity.this, "مشکلی در جستجو پیش آمده", Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }

    private void autoCompleteSearch() {
        mapService.autoCompleteSearch(
                new SearchRequest.Builder("بان قلب")
                        .select(SelectOptions.POI)
                        .select(SelectOptions.ROADS)
                        .filter(FilterOptions.PROVINCE, "تهران")
                        .build(),
                new ResponseListener<AutoCompleteSearchResponse>() {
                    @Override
                    public void onSuccess(AutoCompleteSearchResponse response) {
                        Toast.makeText(MainActivity.this, "پاسخ جستجوی سریع دریافت شد", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(MapirError error) {
                        Toast.makeText(MainActivity.this, "مشکلی در جستجوی سریع پیش آمده", Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }

    private void geofence() {
        mapService.geofence(35.72986153843338, 51.40631675720215, new ResponseListener<GeofenceResponse>() {
            @Override
            public void onSuccess(GeofenceResponse response) {
                Toast.makeText(MainActivity.this, "پاسخ حصار جغرافیایی دریافت شد", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(MapirError error) {
                Toast.makeText(MainActivity.this, "مشکلی در حصار جغرافیایی پیش آمده", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void staticMap() {
        mapService.staticMap(35.808208, 51.507911, 800, 1200, 18, "2", StaticMapMarker.PINK, new ResponseListener<StaticMapResponse>() {
            @Override
            public void onSuccess(StaticMapResponse response) {
                // ((ImageView) findViewById(R.id.sample_img)).setImageBitmap(response.getBitmapStaticMap());
                Toast.makeText(MainActivity.this, "پاسخ کروکی نقشه دریافت شد", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(MapirError error) {
                Toast.makeText(MainActivity.this, "مشکلی در کروکی نقشه پیش آمده", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void distanceMatrix() {
        List<DistanceMatrixPointRequest> tempOrigins = new ArrayList<>();
        tempOrigins.add(new DistanceMatrixPointRequest(1, 35.808208, 51.507911));
        tempOrigins.add(new DistanceMatrixPointRequest(2, 35.808207, 51.500098));
        tempOrigins.add(new DistanceMatrixPointRequest(3, 35.804201, 51.461849));
        tempOrigins.add(new DistanceMatrixPointRequest(4, 35.780042, 51.414385));

        List<DistanceMatrixPointRequest> tempDestinations = new ArrayList<>();
        tempDestinations.add(new DistanceMatrixPointRequest(5, 35.677769, 51.266842));
        tempDestinations.add(new DistanceMatrixPointRequest(6, 35.643731, 51.383057));
        tempDestinations.add(new DistanceMatrixPointRequest(7, 35.648619, 51.479530));
        tempDestinations.add(new DistanceMatrixPointRequest(8, 35.676652, 51.373959));

        mapService.distanceMatrix(
                new DistanceMatrixRequest.Builder(tempOrigins, tempDestinations)
                        .sorted(false)
                        .filter(DistanceMatrixOutputType.DISTANCE)
                        .build(),
                new ResponseListener<DistanceMatrixResponse>() {
                    @Override
                    public void onSuccess(DistanceMatrixResponse response) {
                        Toast.makeText(MainActivity.this, "پاسخ ماتریس فاصله دریافت شد", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(MapirError error) {
                        Toast.makeText(MainActivity.this, "مشکلی در ماتریس فاصله پیش آمده", Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }

    private void route() {
        RouteRequest requestBody = new RouteRequest.Builder(
                35.740312, 51.422625,
                35.722580, 51.451678,
                RouteType.DRIVING
        ).alternative(true)
                .addDestination(35.734826, 51.311946)
                .addDestination(35.699686, 51.337523)
                .build();

        mapService.route(requestBody, new ResponseListener<RouteResponse>() {
            @Override
            public void onSuccess(RouteResponse response) {
                Toast.makeText(MainActivity.this, "پاسخ مسیریابی دریافت شد", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(MapirError error) {
                Toast.makeText(MainActivity.this, "مشکلی در مسیریابی پیش آمده", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void estimatedTimeArrival() {
        mapService.estimatedTimeArrival(
                new EstimatedTimeArrivalRequest.Builder(35.808208, 51.507911)
                        .addDestination(35.808206, 51.500098)
                        .addDestination(35.804201, 51.461849)
                        .addDestination(35.780042, 51.414385)
                        .addDestination(35.677769, 51.266842)
                        .addDestination(35.643731, 51.383057)
                        .addDestination(35.648619, 51.479530)
                        .addDestination(35.676652, 51.373959)
                        .build(),
                new ResponseListener<EstimatedTimeArrivalResponse>() {
                    @Override
                    public void onSuccess(EstimatedTimeArrivalResponse response) {
                        Toast.makeText(MainActivity.this, "پاسخ تخمین زمان رسیدن دریافت شد", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(MapirError error) {
                        Toast.makeText(MainActivity.this, "مشکلی در تخمین زمان رسیدن پیش آمده", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}