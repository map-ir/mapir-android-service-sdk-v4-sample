package ir.map.servicesdk.sample.java;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import ir.map.servicesdk.MapService;
import ir.map.servicesdk.sample.R;

public class MainActivity extends AppCompatActivity {

    private MapService mapirService = new MapService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        sampleReverseGeoCode();
//        sampleFastReverseGeoCode();
//        samplePlaqueReverseGeoCode();


//        mapirService.search(
//                new SearchRequest.Builder("خیابان انقلاب")
//                        .select(SelectOptions.POI)
//                        .select(SelectOptions.ROADS)
//                        .filter(FilterOptions.DISTANCE, 2000, DistanceUnit.METER)
//                        .location(35.701499, 51.419921)
//                        .build(),
//                new ResponseListener<SearchResponse>() {
//                    @Override
//                    public void onSuccess(SearchResponse response) {
//                        Toast.makeText(MainActivity.this, response.getSearchItems().get(0).getAddress(), Toast.LENGTH_SHORT).show();
//                        Log.e("ApiCallz", "Search: Success");
//                    }
//
//                    @Override
//                    public void onError(MapirError error) {
//                        Log.e("ApiCallz", "Search: Error");
//                    }
//                }
//        );
//
//        mapirService.autoCompleteSearch(
//                new SearchRequest.Builder("بان قلب")
//                        .select(SelectOptions.POI)
//                        .select(SelectOptions.ROADS)
//                        .filter(FilterOptions.PROVINCE, "تهران")
//                        .build(),
//                new ResponseListener<AutoCompleteSearchResponse>() {
//                    @Override
//                    public void onSuccess(AutoCompleteSearchResponse response) {
//                        Log.e("ApiCallz", "AutoCompleteSearch: Success");
//                    }
//
//                    @Override
//                    public void onError(MapirError error) {
//                        Log.e("ApiCallz", "AutoCompleteSearch: Error");
//                    }
//                }
//        );
//
//        mapirService.geofence(35.72986153843338, 51.40631675720215, new ResponseListener<GeofenceResponse>() {
//            @Override
//            public void onSuccess(GeofenceResponse response) {
//                Log.e("ApiCallz", "Geofence: Success");
//            }
//
//            @Override
//            public void onError(MapirError error) {
//                Log.e("ApiCallz", "Geofence: Error");
//            }
//        });
//
//        mapirService.reverseGeoCode(35.1213654, 51.236548, new ResponseListener<ReverseGeoCodeResponse>() {
//            @Override
//            public void onSuccess(ReverseGeoCodeResponse response) {
//                Log.e("ApiCallz", "ReverseGeoCode: Success");
//            }
//
//            @Override
//            public void onError(MapirError error) {
//                Log.e("ApiCallz", "ReverseGeoCode: Error");
//            }
//        });
//        mapirService.fastReverseGeoCode(35.807665, 51.507960, new ResponseListener<FastReverseGeoCodeResponse>() {
//            @Override
//            public void onSuccess(FastReverseGeoCodeResponse response) {
//                Log.e("ApiCallz", "FastReverseGeoCode: Success");
//            }
//
//            @Override
//            public void onError(MapirError error) {
//                Log.e("ApiCallz", "FastReverseGeoCode: Error");
//            }
//        });
//        mapirService.plaqueReverseGeoCode(35.807665, 51.507960, new ResponseListener<PlaqueReverseGeoCodeResponse>() {
//            @Override
//            public void onSuccess(PlaqueReverseGeoCodeResponse response) {
//                Log.e("ApiCallz", "PlaqueReverseGeoCode: Success");
//            }
//
//            @Override
//            public void onError(MapirError error) {
//                Log.e("ApiCallz", "PlaqueReverseGeoCode: Error");
//            }
//        });
//        mapirService.staticMap(35.808208, 51.507911, 800, 1200, 18, "2", PINK, new ResponseListener<StaticMapResponse>() {
//            @Override
//            public void onSuccess(StaticMapResponse response) {
//                ((ImageView) findViewById(R.id.sample_img)).setImageBitmap(response.getBitmapStaticMap());
//                Log.e("ApiCallz", "StaticMap: Success");
//            }
//
//            @Override
//            public void onError(MapirError error) {
//                Log.e("ApiCallz", "StaticMap: Error");
//            }
//        });
//
//        List<DistanceMatrixPointRequest> tempOrigins = new ArrayList<>();
//        tempOrigins.add(new DistanceMatrixPointRequest(1, 35.808208, 51.507911));
//        tempOrigins.add(new DistanceMatrixPointRequest(2, 35.808207, 51.500098));
//        tempOrigins.add(new DistanceMatrixPointRequest(3, 35.804201, 51.461849));
//        tempOrigins.add(new DistanceMatrixPointRequest(4, 35.780042, 51.414385));
//
//        List<DistanceMatrixPointRequest> tempDestinations = new ArrayList<>();
//        tempDestinations.add(new DistanceMatrixPointRequest(5, 35.677769, 51.266842));
//        tempDestinations.add(new DistanceMatrixPointRequest(6, 35.643731, 51.383057));
//        tempDestinations.add(new DistanceMatrixPointRequest(7, 35.648619, 51.479530));
//        tempDestinations.add(new DistanceMatrixPointRequest(8, 35.676652, 51.373959));
//
//        mapirService.distanceMatrix(
//                new DistanceMatrixRequest.Builder(tempOrigins, tempDestinations)
//                        .sorted(false)
//                        .filter(DistanceMatrixOutputType.DISTANCE)
//                        .build(),
//                new ResponseListener<DistanceMatrixResponse>() {
//                    @Override
//                    public void onSuccess(DistanceMatrixResponse response) {
//                        Log.e("ApiCallz", "DistanceMatrix: Success");
//                    }
//
//                    @Override
//                    public void onError(MapirError error) {
//                        Log.e("ApiCallz", "DistanceMatrix: Error");
//                    }
//                }
//        );
//
//        RouteRequest requestBody = new RouteRequest.Builder(
//                35.740312, 51.422625,
//                35.722580, 51.451678,
//                RouteType.DRIVING
//        ).alternative(true)
//                .addDestination( 35.734826, 51.311946)
//                .addDestination(35.699686, 51.337523)
//                .build();
//
//        mapirService.route(requestBody, new ResponseListener<RouteResponse>() {
//            @Override
//            public void onSuccess(RouteResponse response) {
//                Toast.makeText(MainActivity.this, response.getRoutes().get(0).getDistance().toString(), Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onError(MapirError error) {
//                Toast.makeText(MainActivity.this, error.getErrorMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        mapirService.estimatedTimeArrival(
//                new EstimatedTimeArrivalRequest.Builder(35.808208, 51.507911)
//                        .addDestination(35.808207, 51.500098)
//                        .addDestination(35.804201, 51.461849)
//                        .addDestination(35.780042, 51.414385)
//                        .addDestination(35.677769, 51.266842)
//                        .addDestination(35.643731, 51.383057)
//                        .addDestination(35.648619, 51.479530)
//                        .addDestination(35.676652, 51.373959)
//                        .build(),
//                new ResponseListener<EstimatedTimeArrivalResponse>() {
//                    @Override
//                    public void onSuccess(EstimatedTimeArrivalResponse response) {
//                        Log.e("ApiCallz", "EstimatedTimeArrival: Success");
//                    }
//
//                    @Override
//                    public void onError(MapirError error) {
//                        Log.e("ApiCallz", "EstimatedTimeArrival: Error");
//                    }
//                });
    }
}
