package ir.map.servicesdk

import android.content.Context
import android.os.Build

class MapirService {
    companion object{
        @JvmStatic
        var apiKey: String? = null
        var userAgent: String? = null

        @JvmStatic
        fun init(context: Context, apiKey: String) {
            if (apiKey != null && apiKey.isNotEmpty()) {
                if (context != null) {
                    Companion.apiKey = apiKey
                    userAgent = userAgentString(context)
                } else throw RuntimeException("Provide nonNull Context.")
            } else throw RuntimeException("No APIKEY Provided, Please visit https://corp.map.ir/registration/ to get new APIKEY")
        }

        @JvmStatic
        private fun userAgentString(context: Context) = String.format("Android/%s(%s)(%s)-ServiceSdk-ktx/%s-%s(%s)/%s-(%s)",
                Build.VERSION.SDK_INT,
                Build.VERSION.RELEASE,
                Build.CPU_ABI,
                BuildConfig.MAPIR_SERVICE_SDK_VERSION,
                context.packageName,
                getApplicationName(context),
                Build.BRAND,
                Build.MODEL
        )

        private fun getApplicationName(context: Context): String? {
            return context.applicationInfo.loadLabel(context.packageManager).toString()
        }
    }
}