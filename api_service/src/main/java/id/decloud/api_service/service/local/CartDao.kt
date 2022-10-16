package id.decloud.api_service.service.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import id.decloud.common.entity.room_table.CartEntity
import id.decloud.common.entity.room_table.TransactionDetailEntity
import id.decloud.common.entity.room_table.TransactionHeaderEntity

@Dao
interface CartDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertCart(cartEntity: CartEntity)

    @Query("SELECT * FROM cart WHERE user=:user")
    fun getCart(user: String): LiveData<List<CartEntity>>

    @Query("DELETE FROM cart")
    fun deleteCart(): Unit

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertTransactionHeader(transactionHeaderTable: TransactionHeaderEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertTransactionDetail(transactionDetailTable: List<TransactionDetailEntity>)

//    @Query("DELETE FROM cart WHERE user=:user")
//    fun deleteCart(user: String): Boolean
//
//    @Query("SELECT EXISTS(SELECT * FROM cart WHERE user=:user AND productCode=:productCode)")
//    fun isExistInCart(user: String, productCode:String): Boolean
}