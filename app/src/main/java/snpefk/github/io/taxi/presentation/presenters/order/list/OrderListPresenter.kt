package snpefk.github.io.taxi.presentation.presenters.order.list

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import snpefk.github.io.taxi.AppServiceLocator
import snpefk.github.io.taxi.domain.entity.Order
import snpefk.github.io.taxi.domain.interactor.OrderInteractor

@InjectViewState
class OrderListPresenter(
    private val interactor: OrderInteractor = AppServiceLocator.orderInteractor
) : MvpPresenter<OrderListView>() {

    override fun onFirstViewAttach() {
        interactor.getAllSortedByDateTime()
            .toList()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(onSuccess = viewState::showOrders, onError = ::handleError)
    }

    fun onOrderClicked(order: Order) {
        viewState.openDetails(order)
    }

    private fun handleError(t: Throwable) {
        viewState.showMsg("Не удалось получить данные")
    }
}