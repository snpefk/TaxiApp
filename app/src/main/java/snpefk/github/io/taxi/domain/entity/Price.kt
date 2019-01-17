package snpefk.github.io.taxi.domain.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.math.BigDecimal
import java.util.*

@Parcelize
class Price(
    val amount: BigDecimal,
    val currency: Currency
) : Parcelable