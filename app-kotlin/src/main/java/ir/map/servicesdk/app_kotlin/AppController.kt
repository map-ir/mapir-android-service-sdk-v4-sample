package ir.map.servicesdk.app_kotlin

import android.app.Application
import ir.map.servicesdk.MapirService

class AppController : Application() {

    override fun onCreate() {
        super.onCreate()

        //TODO Please add your API_KEY
        MapirService.init(baseContext, getString(R.string.api_key))
    }
}