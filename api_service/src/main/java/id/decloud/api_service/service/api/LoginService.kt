package id.decloud.api_service.service.api

import id.decloud.common.entity.api.login.LoginResponse
import id.decloud.common.request.LoginRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {
    @POST("login")
    suspend fun getLogin(
        @Body() loginRequest: LoginRequest,
    ):Response<LoginResponse>

}