package id.decloud.penjualan.view_model

import android.app.Application
import androidx.lifecycle.LiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import id.decloud.api_service.repository.LoginRepository
import id.decloud.common.entity.LoginEntity
import id.decloud.common.entity.ProductEntity
import id.decloud.common.ui.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    application: Application,
    val loginRepository: LoginRepository
) : BaseViewModel(application){



    fun register(loginEntity: LoginEntity){
        loginRepository.insertUser(loginEntity)
    }

}