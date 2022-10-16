package id.decloud.penjualan.view_model

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.decloud.api_service.usecase.RegisterUseCase
import id.decloud.common.entity.api.register.RegisterResponse
import id.decloud.common.request.LoginRequest
import id.decloud.common.ui.AppResponse
import id.decloud.common.ui.BaseViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    application: Application,
//    val loginRepository: LoginRepository
    private val registerUseCase: RegisterUseCase
) : BaseViewModel(application) {

    val registerLiveData = MutableLiveData<AppResponse<RegisterResponse>>()

//    fun register(loginEntity: LoginEntity){
//        loginRepository.insertUser(loginEntity)
//    }

    fun getRegister(loginRequest: LoginRequest) {
        viewModelScope.launch {
            registerUseCase.invoke(loginRequest).collect {
                registerLiveData.postValue(it)
            }
        }

    }

}