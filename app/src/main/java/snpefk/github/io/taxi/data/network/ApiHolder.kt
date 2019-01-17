package snpefk.github.io.taxi.data.network

import com.google.gson.Gson
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.CallAdapter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

// TODO: redo to proper DI
class ApiHolder(
    baseUrl: String = "https://www.roxiemobile.ru/",
    httpClient: OkHttpClient = OkHttpClient.Builder().build(),
    gson: Gson = Gson(),
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