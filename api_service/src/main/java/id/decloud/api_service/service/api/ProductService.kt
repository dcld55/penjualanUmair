package id.decloud.api_service.service.api

import id.decloud.common.entity.api.product.ProductResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ProductService {
    @GET("product")
    suspend fun getProduct(
        @Header("Authorization") token: String,
        @Query("page") page: Int
    ): Response<ProductResponse>
}