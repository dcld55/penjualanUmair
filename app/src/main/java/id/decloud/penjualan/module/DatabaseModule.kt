package id.decloud.penjualan.module

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import id.decloud.api_service.database.PenjualanDatabase
import id.decloud.common.ext.AppExecutors
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun providesPenjualanDatabase(@ApplicationContext context: Context) = PenjualanDatabase.getInstance(context)

    @Provides
    @Singleton
    fun providesLoginDao(penjualanDatabase: PenjualanDatabase) = penjualanDatabase.loginDao()

    @Provides
    @Singleton
    fun providesProductDao(penjualanDatabase: PenjualanDatabase) = penjualanDatabase.productDao()

    @Provides
    @Singleton
    fun providesExecutor() = AppExecutors()
}