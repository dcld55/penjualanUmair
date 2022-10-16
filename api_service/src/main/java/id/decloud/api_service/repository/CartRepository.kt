package id.decloud.api_service.repository

import androidx.lifecycle.LiveData
import id.decloud.api_service.service.local.CartDao
import id.decloud.common.Count
import id.decloud.common.entity.room_table.CartEntity
import id.decloud.common.entity.room_table.TransactionDetailEntity
import id.decloud.common.entity.room_table.TransactionHeaderEntity
import id.decloud.common.ext.AppExecutors
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class CartRepository(
    private val cartDao: CartDao,
    private val appExecutors: AppExecutors
) {


    fun getAllCart(user: String): LiveData<List<CartEntity>> {
        val localdata = cartDao.getCart(user)


        return localdata
    }

    fun isExist(user: String, productCode: String): Boolean {
        return cartDao.isExistInCart(user,productCode)
    }

    fun insertCart(cart: CartEntity) {
        appExecutors.diskIO.execute {
            cartDao.insertCart(cart)
        }
    }

    fun deleteCart() {
        cartDao.deleteCart()
    }

    fun insertTransaction(cartList: List<CartEntity>, user: String) {
        val docCode = LocalDateTime.now().toString()
        val date = LocalDate.now().toString()
        val docNum = LocalTime.now().toString()
        val transactionDetail = arrayListOf<TransactionDetailEntity>()
        cartList.forEach {
            transactionDetail.add(
                TransactionDetailEntity(
                    documentCode = docCode + it.productCode,
                    documentNumber = docNum,
                    productCode = it.productCode,
                    price = Count.getPrice(it.price, it.discount),
                    quantity = it.quantity,
                    unit = it.unit,
                    sub_total = Count.getSubTotal(it),
                    currency = it.currency
                )
            )
        }
        appExecutors.diskIO.execute {
            cartDao.insertTransactionHeader(
                TransactionHeaderEntity(
                    documentCode = docCode,
                    documentNumber = docNum,
                    user = user,
                    total = Count.getTotal(cartList),
                    date = date
                )
            )
            cartDao.insertTransactionDetail(transactionDetail)
        }
    }
}