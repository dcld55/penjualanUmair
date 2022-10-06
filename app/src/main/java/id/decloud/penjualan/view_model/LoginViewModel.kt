package id.decloud.penjualan.view_model

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.decloud.api_service.repository.LoginRepository
import id.decloud.common.entity.LoginEntity
import id.decloud.common.ui.AppResponse
import id.decloud.common.ui.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    application: Application,
    val loginRepository: LoginRepository
) : BaseViewModel(application) {

    var loginLiveData: LiveData<AppResponse<Boolean>>? = null

    fun login(loginEntity: LoginEntity) {
        viewModelScope.launch {
            loginLiveData = loginRepository.getUser(loginEntity)
        }
    }
}