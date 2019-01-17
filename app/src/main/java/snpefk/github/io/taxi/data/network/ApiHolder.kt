package snpefk.github.io.taxi.data.network

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import org.threeten.bp.LocalDateTime
import retrofit2.CallAdapter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import snpefk.github.io.taxi.data.network.deserializer.LocalDateTimeDeserializer

// TODO: redo to proper DI
class ApiHolder(
    baseUrl: String = "https://www.roxiemobile.ru/",
    httpClient: OkHttpClient = OkHttpClient.Builder().build(),
    gson: Gson = GsonBuilder().registerTypeAdapter(LocalDateTime::class.java, LocalDateTimeDeserializer()).create(),
    callAdapterFactory: CallAdapter.Factory = RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io())
) {
    val api: OrderApi = Retrofit.Builder()
            .client(httpClient)
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(callAdapterFactory)
            .build()
            .create(OrderApi::class.java)
}