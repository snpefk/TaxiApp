package snpefk.github.io.taxi.data.repository.vehicle

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import io.reactivex.Single
import io.reactivex.subjects.SingleSubject
import okhttp3.*
import snpefk.github.io.taxi.domain.entity.Vehicle
import java.io.IOException
import java.io.InputStream

class VehicleRepository(
    private val baseUrl: String = "https://www.roxiemobile.ru/careers/test",
    private val httpClient: OkHttpClient = OkHttpClient.Builder().build()
) {
    fun getPhoto(vehicle: Vehicle): Single<Bitmap> {
        val subject = SingleSubject.create<Bitmap>()

        val request = Request.Builder()
            .url("$baseUrl/images/${vehicle.photo}")
            .build()

        httpClient.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                subject.onError(e)
            }

            override fun onResponse(call: Call, response: Response) {
                val stream: InputStream? = response.body()?.byteStream()
                val bitmap: Bitmap = BitmapFactory.decodeStream(stream)
                subject.onSuccess(bitmap)
            }
        })

        return subject
    }
}