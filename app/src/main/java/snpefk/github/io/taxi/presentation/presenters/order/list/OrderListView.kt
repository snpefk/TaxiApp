package snpefk.github.io.taxi.presentation.presenters.order.list

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import snpefk.github.io.taxi.domain.entity.Order

@StateStrategyType(SkipStrategy::class)
interface OrderListView : MvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showOrders(orders: List<Order>)

    fun showMsg(msg: String)
    fun openDetails(order: Order)
}