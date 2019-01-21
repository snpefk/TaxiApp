package snpefk.github.io.taxi.presentation.presenters.order.details

import android.graphics.Bitmap
import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import snpefk.github.io.taxi.domain.entity.Order

@StateStrategyType(AddToEndSingleStrategy::class)
interface OrderDetailsView : MvpView {
    fun showOrder(order: Order)
    fun showPhoto(bitmap: Bitmap)
    fun showMsg(msg: String)
}