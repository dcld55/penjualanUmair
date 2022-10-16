package id.decloud.penjualan.view_model

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import id.decloud.api_service.data_source.DataStorePreference
import id.decloud.api_service.repository.CartRepository
import id.decloud.api_service.usecase.ProductUseCase
import id.decloud.common.entity.room_table.CartEntity
import id.decloud.common.entity.api.product.Content
import id.decloud.common.ui.BaseViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    application: Application,
    private val productUseCase: ProductUseCase,
    private val dataStorePreference: DataStorePreference,
    private val cartRepository: CartRepository,
//    private val app:PenjualanApp
) : BaseViewModel(application) {
    var productLiveData = MutableLiveData<PagingData<Content>>()
    var tokenStore = MutableLiveData<String>()
    var userStore = MutableLiveData<String>()

    init {
        viewModelScope.launch {
            tokenStore.postValue(dataStorePreference.getTokenString())
            userStore.postValue(dataStorePreference.getUserString())
            println()
        }

    }



    fun getProduct(token: String) {

        if (productLiveData.value == null) {
            viewModelScope.launch {
                productUseCase.invoke(token).cachedIn(viewModelScope).collect {
                    productLiveData.postValue(it)
                }
            }

        } else {
            productLiveData.postValue(productLiveData.value)
        }
    }



    fun insertToCart(user: String, content: Content) {

//        app.repository.insertCart(
//            CartEntity(
//                id = 0,
//                user = user,
//                currency = content.currency,
//                dimension = content.dimension,
//                discount = content.discount,
//                name = content.name,
//                price = content.price,
//                productCode = content.productCode,
//                unit = content.unit
//            )
//        )


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


}