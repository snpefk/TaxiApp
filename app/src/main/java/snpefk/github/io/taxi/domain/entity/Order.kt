package snpefk.github.io.taxi.domain.entity

import org.threeten.bp.LocalDateTime

class Order(
    val id: Long,
    val startAddress: Address,
    val endAddress: Address,
    val price: Price,
    val orderTime: LocalDateTime,
    val vehicle: Vehicle
)