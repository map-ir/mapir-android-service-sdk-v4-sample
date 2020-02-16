package ir.map.servicesdk;

import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.InputStream;

import ir.map.servicesdk.response.StaticMapResponse;

public class BitmapWorkerTask extends AsyncTask<InputStream, Void, StaticMapResponse> {

    private ResponseListener<StaticMapResponse> listener;

    BitmapWorkerTask(ResponseListener<StaticMapResponse> listener) {
        this.listener = listener;
    }

    @Override
    protected StaticMapResponse doInBackground(InputStream... inputStreams) {
        return new StaticMapResponse(inputStreams[0], BitmapFactory.decodeStream(inputStreams[0]));
    }

    @Override
    protected void onPostExecute(StaticMapResponse response) {
        super.onPostExecute(response);

        listener.onSuccess(response);
    }
}
