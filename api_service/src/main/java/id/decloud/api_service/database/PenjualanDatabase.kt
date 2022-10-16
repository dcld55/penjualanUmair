package id.decloud.api_service.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import id.decloud.api_service.service.local.CartDao
import id.decloud.common.entity.room_table.CartEntity
import id.decloud.common.entity.room_table.TransactionDetailEntity
import id.decloud.common.entity.room_table.TransactionHeaderEntity

@Database(
    entities = [
        CartEntity::class,
        TransactionHeaderEntity::class,
        TransactionDetailEntity::class
    ], version = 1, exportSchema = false
)
abstract class PenjualanDatabase() : RoomDatabase() {
    abstract fun cartDao(): CartDao

    companion object {
        @Volatile
        private var instance: PenjualanDatabase? = null
        fun getInstance(context: Context): PenjualanDatabase =
            instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(
                    context.applicationContext,
                    PenjualanDatabase::class.java, "penjualan.db"
                ).allowMainThreadQueries().build()
            }
    }

}