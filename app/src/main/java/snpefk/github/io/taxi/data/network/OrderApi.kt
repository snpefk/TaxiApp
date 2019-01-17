package snpefk.github.io.taxi.data.network

import io.reactivex.Single
import retrofit2.http.GET
import snpefk.github.io.taxi.domain.entity.Order

interface OrderApi {

    @GET("careers/test/orders.json")
    fun getOrders(): Single<List<Order>>
}