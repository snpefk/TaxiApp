package snpefk.github.io.taxi.data.repository.order

import io.reactivex.Observable
import snpefk.github.io.taxi.data.network.ApiHolder
import snpefk.github.io.taxi.domain.entity.Order

// todo: repo to proper DI
class OrderRepository(
    private val apiHolder: ApiHolder = ApiHolder()
) {
    fun getAll(): Observable<Order> = apiHolder.api.getOrders()
        .flattenAsObservable { it }
}