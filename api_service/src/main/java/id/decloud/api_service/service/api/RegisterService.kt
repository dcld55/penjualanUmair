package id.decloud.api_service.service.api

import id.decloud.common.entity.api.register.RegisterResponse
import id.decloud.common.request.LoginRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface RegisterService {
    @POST("register")
    suspend fun getRegister(
        @Body() loginRequest: LoginRequest,
    ): Response<RegisterResponse>
}