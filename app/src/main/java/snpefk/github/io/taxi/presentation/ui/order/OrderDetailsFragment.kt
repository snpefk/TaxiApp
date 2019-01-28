package snpefk.github.io.taxi.presentation.ui.order

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import kotlinx.android.synthetic.main.fragment_details_order.*
import kotlinx.android.synthetic.main.item_order.*
import org.threeten.bp.format.DateTimeFormatter
import snpefk.github.io.taxi.AppServiceLocator
import snpefk.github.io.taxi.R
import snpefk.github.io.taxi.domain.entity.Order
import snpefk.github.io.taxi.presentation.presenters.order.details.OrderDetailsPresenter
import snpefk.github.io.taxi.presentation.presenters.order.details.OrderDetailsView

class OrderDetailsFragment : MvpAppCompatFragment(), OrderDetailsView {

    @InjectPresenter
    lateinit var presenter: OrderDetailsPresenter
    private val formatter: DateTimeFormatter = AppServiceLocator.formatter

    @ProvidePresenter
    fun providePresenter(): OrderDetailsPresenter {
        val order: Order = arguments?.getParcelable(ORDER_KEY)?: throw IllegalArgumentException("No value passed for order")
        return OrderDetailsPresenter(order)
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_details_order, container, false)

    @SuppressLint("SetTextI18n")
    override fun showOrder(order: Order) {
        tvAddress.text = "${order.startAddress.address} — ${order.endAddress.address}"
        tvOrderTime.text = order.orderTime.format(formatter)
        tvPrice.text = "${order.price.amount.movePointLeft(2)} ${order.price.currency.symbol}"
        tvVehicle.text = "${order.vehicle.modelName} «${order.vehicle.regNumber}»"
        tvDriver.text = order.vehicle.driverName
    }

    override fun showPhoto(bitmap: Bitmap) {
        ivPhoto.setImageBitmap(bitmap)
    }

    override fun showMsg(msg: String) {
        Snackbar.make(view!!, msg, Snackbar.LENGTH_SHORT)
            .show()
    }

    companion object {
        const val FRAGMENT_NAME = "OrderDetailsFragment"
        private const val ORDER_KEY = "order"

        fun newInstance(order: Order): OrderDetailsFragment = OrderDetailsFragment().also { fragment ->
            fragment.arguments = Bundle().also { bundle ->
                bundle.putParcelable(ORDER_KEY, order)
            }
        }
    }
}