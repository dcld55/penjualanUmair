package id.decloud.penjualan.view_model

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.decloud.api_service.repository.CartRepository
import id.decloud.api_service.usecase.LoginUseCase
import id.decloud.common.entity.api.login.LoginResponse
import id.decloud.common.request.LoginRequest
import id.decloud.common.ui.AppResponse
import id.decloud.common.ui.BaseViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    application: Application,
    private val loginUseCase: LoginUseCase
) : BaseViewModel(application) {

//    var loginLiveData: LiveData<AppResponse<Boolean>>? = null

//    fun login(loginEntity: LoginEntity) {
//        viewModelScope.launch {
//            loginLiveData = loginRepository.getUser(loginEntity)
//
//        }
//    }


    val loginLiveData = MutableLiveData<AppResponse<LoginResponse>>()

    fun login(loginRequest: LoginRequest) {
        viewModelScope.launch {
            loginUseCase.invoke(loginRequest).collect {
                loginLiveData.postValue(it)
            }
        }
    }

}