package id.decloud.common.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "transaction_header")
class TransactionHeaderEntity(
    @PrimaryKey
    @field:ColumnInfo(name = "document_code")
    val documentCode: String,
    @field:ColumnInfo(name = "document_number")
    val documentNumber: String,
    @field:ColumnInfo(name = "user")
    val user: String,
    @field:ColumnInfo(name = "total")
    val total: Int,
    @field:ColumnInfo(name = "date")
    val date: String
) {
}