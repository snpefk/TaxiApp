package snpefk.github.io.taxi.data.network

import com.google.gson.Gson
import okhttp3.OkHttpClient
import retrofit2.CallAdapter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiHolder(
    baseUrl: String,
    httpClient: OkHttpClient,
    gson: Gson,
    callAdapterFactory: CallAdapter.Factory
) {
    val api: OrderApi = Retrofit.Builder()
            .client(httpClient)
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(callAdapterFactory)
            .build()
            .create(OrderApi::class.java)
}