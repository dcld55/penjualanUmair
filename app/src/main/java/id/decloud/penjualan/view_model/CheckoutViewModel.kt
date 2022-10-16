package id.decloud.penjualan.view_model

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.decloud.api_service.data_source.DataStorePreference
import id.decloud.api_service.repository.CartRepository
import id.decloud.common.entity.room_table.CartEntity
import id.decloud.common.ui.BaseViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CheckoutViewModel @Inject constructor(
    application: Application,
    private val cartRepository: CartRepository,
    private val dataStorePreference: DataStorePreference
) : BaseViewModel(application) {

    var cartLiveData: LiveData<List<CartEntity>>? = null
    var subTotal = hashMapOf<String, CartEntity>()
    var user = MutableLiveData<String>()

    init {
        getCart()


    }

    fun insertIntoTransaction(user: String) {
        viewModelScope.launch {
            cartRepository.insertTransaction(subTotal.values.toList(), user)
        }
    }

    fun getCart() {
        viewModelScope.launch {
            dataStorePreference.getUser().collect {
                cartLiveData = cartRepository.getAllCart(it)
            }
        }
    }

    fun deleteCart() {
        cartRepository.deleteCart()
    }


}