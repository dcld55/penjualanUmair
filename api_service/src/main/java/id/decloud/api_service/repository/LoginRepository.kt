package id.decloud.api_service.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import id.decloud.api_service.service.local.LoginDao
import id.decloud.common.entity.LoginEntity
import id.decloud.common.ext.AppExecutors
import id.decloud.common.ui.AppResponse

class LoginRepository(
    private val loginDao: LoginDao,
    private val appExecutors: AppExecutors
) {

    private val hasilLogin = MediatorLiveData<AppResponse<Boolean>>()

    fun getUser(user: LoginEntity): LiveData<AppResponse<Boolean>> {
        val localdata = loginDao.getLogin(user.user)
        hasilLogin.addSource(localdata) {
            when {
                it == null -> hasilLogin.value =
                    AppResponse.failure(Exception("User tidak ditemukan"))
                it.password != user.password -> hasilLogin.value =
                    AppResponse.failure(Exception("invalid password"))
                else -> hasilLogin.value = AppResponse.success(true)
            }
        }
        return hasilLogin
    }

    fun insertUser(user: LoginEntity) {
        appExecutors.diskIO.execute {
            loginDao.insertLogin(user)
        }
    }
}