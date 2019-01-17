package snpefk.github.io.taxi.domain.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import org.threeten.bp.LocalDateTime

@Parcelize
data class Order(
    val id: Long,
    val startAddress: Address,
    val endAddress: Address,
    val price: Price,
    val orderTime: LocalDateTime,
    val vehicle: Vehicle
) : Parcelable