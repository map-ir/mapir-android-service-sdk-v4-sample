package ir.map.servicesdk.response;

import android.graphics.Bitmap;

import java.io.InputStream;

import ir.map.servicesdk.model.base.MapirResponse;

public class StaticMapResponse extends MapirResponse {

    private InputStream data;
    private Bitmap bitmap;

    public StaticMapResponse(InputStream inputStream, Bitmap bitmap) {
        this.data = inputStream;
        this.bitmap = bitmap;
    }

    public static StaticMapResponse createStaticMapResponse(InputStream data) {
        return new StaticMapResponse(data, null);
    }

    public InputStream getData() {
        return data;
    }

    public Bitmap getBitmapStaticMap() {
        return bitmap;
    }
}