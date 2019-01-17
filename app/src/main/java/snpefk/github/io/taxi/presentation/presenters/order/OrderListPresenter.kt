package snpefk.github.io.taxi.presentation.presenters.order

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import snpefk.github.io.taxi.domain.interactor.OrderInteractor

@InjectViewState
class OrderListPresenter(
    private val interactor: OrderInteractor = OrderInteractor()
) : MvpPresenter<OrderListView>() {

    override fun onFirstViewAttach() {
        interactor.getAllSortedByDateTime()
            .toList()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(onSuccess = viewState::showOrders, onError = ::handleError)
    }

    private fun handleError(t: Throwable) {
        viewState.showMsg("Не удалось получить данные")
    }

}