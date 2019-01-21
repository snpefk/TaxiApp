package snpefk.github.io.taxi.domain.interactor

import android.graphics.Bitmap
import io.reactivex.Single
import snpefk.github.io.taxi.data.repository.vehicle.VehicleRepository
import snpefk.github.io.taxi.domain.entity.Vehicle

class VehicleInteractor(
    private val repository: VehicleRepository = VehicleRepository()
) {
    fun getPhotoBy(vehicle: Vehicle): Single<Bitmap> = repository.getPhoto(vehicle)
}