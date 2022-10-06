package id.decloud.api_service.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import id.decloud.api_service.service.local.LoginDao
import id.decloud.api_service.service.local.ProductDao
import id.decloud.common.entity.LoginEntity
import id.decloud.common.entity.ProductEntity
import id.decloud.common.entity.TransactionDetailEntity
import id.decloud.common.entity.TransactionHeaderEntity

@Database(
    entities = [LoginEntity::class,
        ProductEntity::class,
        TransactionHeaderEntity::class,
        TransactionDetailEntity::class], version = 1, exportSchema = false
)
abstract class PenjualanDatabase() : RoomDatabase() {
    abstract fun loginDao(): LoginDao
    abstract fun productDao(): ProductDao

    companion object {
        @Volatile
        private var instance: PenjualanDatabase? = null
        fun getInstance(context: Context): PenjualanDatabase =
            instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(
                    context.applicationContext,
                    PenjualanDatabase::class.java, "penjualan.db"
                ).build()
            }
    }

}