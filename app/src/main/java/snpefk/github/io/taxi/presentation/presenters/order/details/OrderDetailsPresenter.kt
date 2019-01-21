package snpefk.github.io.taxi.presentation.presenters.order.details

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import snpefk.github.io.taxi.domain.entity.Order
import snpefk.github.io.taxi.domain.interactor.VehicleInteractor

@InjectViewState
class OrderDetailsPresenter(
    private val order: Order,
    private val vehicleInteractor: VehicleInteractor = VehicleInteractor()
) : MvpPresenter<OrderDetailsView>() {

    override fun onFirstViewAttach() {
        viewState.showOrder(order)
        vehicleInteractor.getPhotoBy(order.vehicle)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(onSuccess = viewState::showPhoto, onError = ::handleError)
    }

    private fun handleError(t: Throwable) {
        viewState.showMsg("Не удалось получить данные")
    }
}