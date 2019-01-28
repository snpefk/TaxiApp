package snpefk.github.io.taxi.data.repository.order

import io.reactivex.Observable
import snpefk.github.io.taxi.data.network.ApiHolder
import snpefk.github.io.taxi.domain.entity.Order

class OrderRepository(
    private val apiHolder: ApiHolder
) {

    fun getAll(): Observable<Order> = apiHolder.api.getOrders()
        .flattenAsObservable { it }
}