package snpefk.github.io.taxi.domain.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Address(
    val city: String,
    val address: String
): Parcelable