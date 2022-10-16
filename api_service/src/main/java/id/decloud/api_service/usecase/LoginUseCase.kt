package id.decloud.api_service.usecase

import id.decloud.api_service.data_source.DataStorePreference
import id.decloud.api_service.service.api.LoginService
import id.decloud.common.entity.api.login.LoginResponse
import id.decloud.common.request.LoginRequest
import id.decloud.common.ui.AppResponse
import kotlinx.coroutines.flow.flow

class LoginUseCase(
    private val loginService: LoginService,
    private val storePreference: DataStorePreference
) {
    operator fun invoke(loginRequest: LoginRequest) = flow<AppResponse<LoginResponse>> {
        try {
            emit(AppResponse.loading())
            val result = loginService.getLogin(loginRequest)
            if (result.isSuccessful) {
                result.body()?.let {
                    emit(AppResponse.success(it))
                    storePreference.saveToken(it.loginResult.token)
                    storePreference.saveUser(it.loginResult.username)
                }
            } else {
                emit(AppResponse.failure(Exception("Invalid Username or Password")))
            }
        } catch (e: Exception) {
            emit(AppResponse.failure(e))
        }
    }
}