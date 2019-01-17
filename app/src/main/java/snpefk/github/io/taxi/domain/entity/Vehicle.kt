package snpefk.github.io.taxi.domain.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Vehicle(
    val regNumber: String,
    val modelName: String,
    val photo: String,
    val driverName: String
): Parcelable