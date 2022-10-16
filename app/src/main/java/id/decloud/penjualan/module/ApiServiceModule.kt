package id.decloud.penjualan.module

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import id.decloud.api_service.service.api.LoginService
import id.decloud.api_service.service.api.ProductService
import id.decloud.api_service.service.api.RegisterService
import id.decloud.api_service.service.api.RetrofitClient
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiServiceModule {

    @Provides
    @Singleton
    fun provideRetrofit(@ApplicationContext context: Context) = RetrofitClient.getClient(context)

    @Provides
    @Singleton
    fun provideLoginService(retrofit: Retrofit) = retrofit.create(LoginService::class.java)

    @Provides
    @Singleton
    fun provideRegisterService(retrofit: Retrofit) = retrofit.create(RegisterService::class.java)

    @Provides
    @Singleton
    fun provideProductService(retrofit: Retrofit) = retrofit.create(ProductService::class.java)
}