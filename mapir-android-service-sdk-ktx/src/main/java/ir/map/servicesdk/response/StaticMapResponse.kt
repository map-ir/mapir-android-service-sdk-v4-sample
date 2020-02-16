package ir.map.servicesdk.response

import android.graphics.Bitmap
import ir.map.servicesdk.model.base.MapirResponse
import java.io.InputStream

class StaticMapResponse(val data: InputStream, val bitmapStaticMap: Bitmap? = null) : MapirResponse() {
    companion object {
        fun createStaticMapResponse(data: InputStream) = StaticMapResponse(data, null)
    }
}