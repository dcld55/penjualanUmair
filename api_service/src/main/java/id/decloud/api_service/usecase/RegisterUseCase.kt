package id.decloud.api_service.usecase

import id.decloud.api_service.service.api.RegisterService
import id.decloud.common.entity.api.register.RegisterResponse
import id.decloud.common.request.LoginRequest
import id.decloud.common.ui.AppResponse
import kotlinx.coroutines.flow.flow

class RegisterUseCase (
    private val registerService: RegisterService
) {
    operator fun invoke(loginRequest: LoginRequest) = flow<AppResponse<RegisterResponse>> {
        try {
            emit(AppResponse.loading())
            val result = registerService.getRegister(loginRequest)
            if (result.isSuccessful) {
                result.body()?.let {
                    emit(AppResponse.success(it))
                }
            }
        } catch (e: Exception) {
            emit(AppResponse.failure(e))
        }
    }
}