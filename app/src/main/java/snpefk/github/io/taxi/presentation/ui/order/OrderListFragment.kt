package snpefk.github.io.taxi.presentation.ui.order

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.fragment_list_order.*
import snpefk.github.io.taxi.BuildConfig
import snpefk.github.io.taxi.R
import snpefk.github.io.taxi.domain.entity.Order
import snpefk.github.io.taxi.domain.interactor.OrderInteractor


class OrderListFragment : Fragment() {

    private val orderAdapter = OrderAdapter()
    private val interactor: OrderInteractor = OrderInteractor()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_list_order, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvOrders.setHasFixedSize(false)
        rvOrders.adapter = orderAdapter
        rvOrders.layoutManager = LinearLayoutManager(activity)
        rvOrders.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))

        interactor.getAllSortedByDateTime()
            .toList()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(onSuccess = orderAdapter::setData, onError = ::handleError)
    }

    private fun handleError(t: Throwable) {
        if (BuildConfig.DEBUG) {
            t.printStackTrace()
        }
        Snackbar.make(view!!,"Не удалось получить данные", Toast.LENGTH_SHORT)
            .show()
    }

    private class OrderAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

        private var orders: List<Order> = emptyList()

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            val view: View = LayoutInflater.from(parent.context)
                .inflate(android.R.layout.simple_list_item_2, parent, false)

            return OrderViewHolder(view)
        }

        override fun getItemCount(): Int = orders.size

        override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
            val order = orders[position]
            (viewHolder as OrderViewHolder).bind(order)
        }

        fun setData(orders: List<Order>) {
            this.orders = orders
            notifyDataSetChanged() // potential bottleneck replace with DiffUtils
        }

        private class OrderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            private val text1: TextView = itemView.findViewById(android.R.id.text1)
            private val text2: TextView = itemView.findViewById(android.R.id.text2)

            fun bind(order: Order) {
                text1.text = "${order.startAddress.address} - ${order.endAddress.address}"
                text2.text = "${order.orderTime}"
            }
        }
    }
    companion object {
        const val FRAGMENT_NAME = "OrderListFragment"
    }
}