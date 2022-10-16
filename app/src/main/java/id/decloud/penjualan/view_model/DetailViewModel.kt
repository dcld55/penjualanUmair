package id.decloud.penjualan.view_model

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.decloud.api_service.data_source.DataStorePreference
import id.decloud.api_service.repository.CartRepository
import id.decloud.common.entity.api.product.Content
import id.decloud.common.entity.room_table.CartEntity
import id.decloud.common.ui.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    application: Application,
    private val dataStorePreference: DataStorePreference,
    val cartRepository: CartRepository
) : BaseViewModel(application) {

    val user = MutableLiveData<String>()

    init {
        viewModelScope.launch {
            user.postValue(dataStorePreference.getUserString())
        }
    }

    fun insertToCart(user: String, content: Content) {

        cartRepository.insertCart(
            CartEntity(
                id = 0,
                user = user,
                currency = content.currency,
                dimension = content.dimension,
                discount = content.discount,
                name = content.name,
                price = content.price,
                productCode = content.productCode,
                unit = content.unit,
                quantity = 1
            )
        )
    }

    fun isExistInCart(user: String, productCode: String):Boolean{
        return cartRepository.isExist(user,productCode)
    }

}