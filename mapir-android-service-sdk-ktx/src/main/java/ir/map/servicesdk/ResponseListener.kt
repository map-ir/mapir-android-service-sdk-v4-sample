package ir.map.servicesdk

import ir.map.servicesdk.model.base.BaseInterface
import ir.map.servicesdk.model.base.MapirError
import ir.map.servicesdk.model.base.MapirResponse

interface ResponseListener<T : MapirResponse> : BaseInterface {
    fun onSuccess(response: T)
    fun onError(error: MapirError)
}