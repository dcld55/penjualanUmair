package id.decloud.common

import id.decloud.common.entity.room_table.CartEntity

object Count {
    fun getSubTotal(cartEntity: CartEntity): Double {
        return (cartEntity.price -(cartEntity.price * cartEntity.discount / 100)) * cartEntity.quantity
    }
    fun getPrice(price: Double, discount: Double) = price - (price * discount / 100)

    fun getTotal(subTotalList: List<CartEntity>): Double {
        var total = 0.0
        subTotalList.forEach {
            total += getSubTotal(it)

        }
        return total
    }
}