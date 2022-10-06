package id.decloud.common.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.navigation.NavDirections
import id.decloud.common.ext.SingleLiveEvent

open class BaseViewModel(application: Application) : AndroidViewModel(application) {
    val navigationEvent = SingleLiveEvent<NavDirections>()
    val popBackStackEvent = SingleLiveEvent<Any>()

    fun navigate(nav: NavDirections) {
        navigationEvent.postValue(nav)
    }


    fun popBackStack() {
        popBackStackEvent.postValue(Any())
    }

}