package id.decloud.penjualan.view_model

import android.app.Application
import androidx.lifecycle.LiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import id.decloud.api_service.repository.ProductRepository
import id.decloud.common.entity.ProductEntity
import id.decloud.common.ui.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    application: Application,
    val productRepository: ProductRepository
) : BaseViewModel(application) {
    var productLiveData: LiveData<List<ProductEntity>>? = null

    init {
        productLiveData = productRepository.getAllProduct()
    }
}