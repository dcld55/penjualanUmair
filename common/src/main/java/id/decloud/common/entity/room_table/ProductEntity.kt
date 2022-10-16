package id.decloud.common.entity.room_table

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product")
class ProductEntity(
    @PrimaryKey
    @field:ColumnInfo(name = "product_code")
    val productCode: String,
    @field:ColumnInfo(name = "product_name")
    val productName: String,
    @field:ColumnInfo(name = "price")
    val price: Long,
    @field:ColumnInfo(name = "currency")
    val currency: String,
    @field:ColumnInfo(name = "discount")
    val discount: Long,
    @field:ColumnInfo(name = "dimension")
    val domension: String,
    @field:ColumnInfo(name = "unit")
    val unit: String
)