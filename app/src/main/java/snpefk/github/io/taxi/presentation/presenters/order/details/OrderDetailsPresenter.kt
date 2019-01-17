package snpefk.github.io.taxi.presentation.presenters.order.details

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import snpefk.github.io.taxi.domain.entity.Order

@InjectViewState
class OrderDetailsPresenter(
    val order: Order
) : MvpPresenter<OrderDetailsView>() {

    override fun onFirstViewAttach() {
        viewState.showOrder(order)
    }
}