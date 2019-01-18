package snpefk.github.io.taxi.presentation.ui.order

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import kotlinx.android.synthetic.main.fragment_list_order.*
import kotlinx.android.synthetic.main.item_order.view.*
import org.threeten.bp.format.DateTimeFormatter
import snpefk.github.io.taxi.R
import snpefk.github.io.taxi.domain.entity.Order
import snpefk.github.io.taxi.presentation.presenters.order.list.OrderListPresenter
import snpefk.github.io.taxi.presentation.presenters.order.list.OrderListView

class OrderListFragment : MvpAppCompatFragment(),
    OrderListView {

    @InjectPresenter
    lateinit var presenter: OrderListPresenter

    private val orderAdapter by lazy { OrderAdapter(presenter::onOrderClicked) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_list_order, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvOrders.setHasFixedSize(false)
        rvOrders.adapter = orderAdapter
        rvOrders.layoutManager = LinearLayoutManager(activity)
        rvOrders.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
    }

    override fun showOrders(orders: List<Order>) {
        orderAdapter.setData(orders)
    }

    override fun showMsg(msg: String) {
        Snackbar.make(view!!, msg, Toast.LENGTH_SHORT)
            .show()
    }

    override fun openDetails(order: Order) {
        val fragmentManager = activity?.supportFragmentManager ?: return
        val fragment = OrderDetailsFragment.newInstance(order)

        fragmentManager.beginTransaction()
            .replace(R.id.flContent, fragment, OrderDetailsFragment.FRAGMENT_NAME)
            .addToBackStack(OrderDetailsFragment.FRAGMENT_NAME)
            .commit()
    }

    private class OrderAdapter(private val onClick: (Order) -> Unit) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

        private var orders: List<Order> = emptyList()
        private val formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy") // todo: replace with inject

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            val view: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_order, parent, false)

            return OrderViewHolder(view, onClick, formatter)
        }

        override fun getItemCount(): Int = orders.size

        override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
            val order = orders[position]
            (viewHolder as OrderViewHolder).bind(order)
        }

        fun setData(orders: List<Order>) {
            this.orders = orders
            notifyDataSetChanged() // potential bottleneck; replace with DiffUtils
        }

        private class OrderViewHolder(
            itemView: View,
            private val onClick: (Order) -> Unit,
            private val formatter: DateTimeFormatter
        ) : RecyclerView.ViewHolder(itemView) {

            lateinit var order: Order

            init {
                itemView.setOnClickListener { onClick(order) }
            }

            @SuppressLint("SetTextI18n")
            fun bind(order: Order) {
                this.order = order
                itemView.tvAddress.text = "${order.startAddress.address} — ${order.endAddress.address}"
                itemView.tvOrderTime.text = order.orderTime.format(formatter)
                itemView.tvPrice.text = "${order.price.amount.movePointLeft(2)} ${order.price.currency.symbol}"
            }
        }
    }

    companion object {
        const val FRAGMENT_NAME = "OrderListFragment"
    }
}