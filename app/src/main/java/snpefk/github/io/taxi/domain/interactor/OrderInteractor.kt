package snpefk.github.io.taxi.domain.interactor

import io.reactivex.Observable
import snpefk.github.io.taxi.data.repository.order.OrderRepository
import snpefk.github.io.taxi.domain.entity.Order

class OrderInteractor(
    private val repository: OrderRepository = OrderRepository()
) {

    fun getAllSortedByDateTime(): Observable<Order> = repository.getAll()
        .sorted { order1, order2 -> order2.orderTime.compareTo(order1.orderTime) }
}