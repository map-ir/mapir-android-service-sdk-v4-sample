package ir.map.servicesdk

import android.graphics.BitmapFactory
import android.os.AsyncTask
import ir.map.servicesdk.response.StaticMapResponse
import java.io.InputStream

internal class BitmapWorkerTask (private val listener: ResponseListener<StaticMapResponse>) : AsyncTask<InputStream?, Void?, StaticMapResponse?>() {
    override fun doInBackground(vararg inputStreams: InputStream?): StaticMapResponse? {
        return inputStreams[0]?.let { StaticMapResponse(it, BitmapFactory.decodeStream(inputStreams[0])) }
    }

    override fun onPostExecute(response: StaticMapResponse?) {
        super.onPostExecute(response)

        response?.let { listener.onSuccess(it) }
    }

}