package id.decloud.penjualan

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import id.decloud.api_service.database.PenjualanDatabase
import id.decloud.api_service.repository.CartRepository
import id.decloud.common.ext.AppExecutors
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

@HiltAndroidApp
class PenjualanApp : Application() {
//    val appx by lazy { AppExecutors() }
//    val database by lazy { PenjualanDatabase.getInstance(this) }
//    val repository by lazy { CartRepository(database.cartDao(), appx) }
}