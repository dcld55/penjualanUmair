package id.decloud.common.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transaction_detail")
class TransactionDetailEntity(
    @PrimaryKey
    @field:ColumnInfo(name = "document_code")
    val documentCode: String,
    @field:ColumnInfo(name = "document_number")
    val documentNumber: String,
    @field:ColumnInfo(name = "product_code")
    val productCode: String,
    @field:ColumnInfo(name = "price")
    val price: Long,
    @field:ColumnInfo(name = "quantity")
    val quantity: Int,
    @field:ColumnInfo(name = "unit")
    val unit: String,
    @field:ColumnInfo(name = "sub_total")
    val sub_total: Long,
    @field:ColumnInfo(name = "currency")
    val currency: String,
) {

}