package ir.map.servicesdk;

import ir.map.servicesdk.model.base.MapirError;

public interface ResponseListener<T> {
    void onSuccess(T response);

    void onError(MapirError error);
}
