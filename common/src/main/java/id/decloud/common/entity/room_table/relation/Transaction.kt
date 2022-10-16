package id.decloud.common.entity.room_table.relation

import androidx.room.Embedded
import androidx.room.Relation
import id.decloud.common.entity.room_table.TransactionDetailEntity
import id.decloud.common.entity.room_table.TransactionHeaderEntity

data class Transaction(
    @Embedded
    val transactionHeaderEntity: TransactionHeaderEntity,

    @Relation(
        parentColumn = "document_number",
        entityColumn = "document_number"
    )

    val transactionDetailEntity: List<TransactionDetailEntity>
)