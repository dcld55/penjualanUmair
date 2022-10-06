package id.decloud.penjualan.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import id.decloud.api_service.repository.LoginRepository
import id.decloud.api_service.repository.ProductRepository
import id.decloud.api_service.service.local.LoginDao
import id.decloud.api_service.service.local.ProductDao
import id.decloud.common.ext.AppExecutors

@Module
@InstallIn(ViewModelComponent::class)
class RepositoryModule {

    @Provides
    fun provideLoginRepository(loginDao: LoginDao, executors: AppExecutors) =
        LoginRepository(loginDao, executors)

    @Provides
    fun provideProductRepository(productDao: ProductDao, executors: AppExecutors) =
        ProductRepository(productDao, executors)
}