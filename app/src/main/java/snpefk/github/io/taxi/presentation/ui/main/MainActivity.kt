package snpefk.github.io.taxi.presentation.ui.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import snpefk.github.io.taxi.R
import snpefk.github.io.taxi.presentation.ui.order.OrderListFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.flContent, OrderListFragment(), OrderListFragment.FRAGMENT_NAME)
            .commit()
    }
}
