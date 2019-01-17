package snpefk.github.io.taxi.domain.entity

class Order(
    val id: Long,
    val startAddress: Address,
    val endAddress: Address,
    val price: Price,
    val vehicle: Vehicle
)