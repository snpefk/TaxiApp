package snpefk.github.io.taxi.presentation.presenters.order

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import snpefk.github.io.taxi.domain.entity.Order

@StateStrategyType(AddToEndSingleStrategy::class)
interface OrderListView : MvpView {

    fun showOrders(orders: List<Order>)

    @StateStrategyType(SkipStrategy::class)
    fun showMsg(msg: String)
}