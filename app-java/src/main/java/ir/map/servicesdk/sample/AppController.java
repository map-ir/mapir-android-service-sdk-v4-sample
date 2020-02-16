package ir.map.servicesdk.sample;

import android.app.Application;

import ir.map.servicesdk.MapirService;

public class AppController extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //TODO Please add your API_KEY
        MapirService.init(getBaseContext(), getString(R.string.api_key));
    }
}
