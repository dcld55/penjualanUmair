package id.decloud.penjualan.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import id.decloud.api_service.data_source.DataStorePreference
import id.decloud.api_service.repository.CartRepository
import id.decloud.api_service.service.api.LoginService
import id.decloud.api_service.service.api.ProductService
import id.decloud.api_service.service.api.RegisterService
import id.decloud.api_service.service.local.CartDao
import id.decloud.api_service.usecase.InitDataUseCase
import id.decloud.api_service.usecase.LoginUseCase
import id.decloud.api_service.usecase.ProductUseCase
import id.decloud.api_service.usecase.RegisterUseCase
import id.decloud.common.ext.AppExecutors

@Module
@InstallIn(ViewModelComponent::class)
class UseCaseModule {
    @Provides
    fun provideLoginUsecase(loginService: LoginService, dataStorePreference: DataStorePreference) =
        LoginUseCase(loginService, dataStorePreference)

    @Provides
    fun provideRegisterUsecase(registerService: RegisterService) = RegisterUseCase(registerService)

    @Provides
    fun provideProductUsecase(productService: ProductService) = ProductUseCase(productService)

    @Provides
    fun provideInitProductUseCase(dataStorePreference: DataStorePreference) = InitDataUseCase(dataStorePreference)

    @Provides
    fun provideProductRepository(cartDao: CartDao, executors: AppExecutors) =
        CartRepository(cartDao, executors)
}