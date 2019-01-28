package snpefk.github.io.taxi

import android.graphics.Bitmap
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import org.threeten.bp.Duration
import org.threeten.bp.LocalDateTime
import org.threeten.bp.format.DateTimeFormatter
import retrofit2.CallAdapter
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import snpefk.github.io.taxi.data.cache.TimeBoundedCache
import snpefk.github.io.taxi.data.network.ApiHolder
import snpefk.github.io.taxi.data.network.deserializer.LocalDateTimeDeserializer
import snpefk.github.io.taxi.data.repository.order.OrderRepository
import snpefk.github.io.taxi.data.repository.vehicle.VehicleRepository
import snpefk.github.io.taxi.domain.interactor.OrderInteractor
import snpefk.github.io.taxi.domain.interactor.VehicleInteractor

// poor man choice dependency inversion
object AppServiceLocator {
    val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("dd LLLL yyyy")

    // network dependency
    val baseUrl = "https://www.roxiemobile.ru/careers/test"
    val httpClient: OkHttpClient by lazy { OkHttpClient.Builder().build() }
    val cache: TimeBoundedCache<String, Bitmap> by lazy {
        TimeBoundedCache<String, Bitmap>(Duration.ofMinutes(10))
    }
    val gson: Gson by lazy {
        GsonBuilder()
            .registerTypeAdapter(LocalDateTime::class.java, LocalDateTimeDeserializer())
            .create()
    }
    val schedulers by lazy { Schedulers.io() }
    val callAdapterFactory: CallAdapter.Factory by lazy { RxJava2CallAdapterFactory.createWithScheduler(schedulers) }
    val apiHolder by lazy { ApiHolder(baseUrl, httpClient, gson, callAdapterFactory) }

    // repository
    val orderRepository by lazy { OrderRepository(apiHolder) }
    val vehicleRepository by lazy { VehicleRepository(baseUrl, httpClient, cache) }

    // interactor
    val orderInteractor by lazy { OrderInteractor(orderRepository) }
    val vehicleInteractor by lazy { VehicleInteractor(vehicleRepository) }
}