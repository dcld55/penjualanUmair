package id.decloud.penjualan.view_model

import android.app.Application
import androidx.lifecycle.LiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import id.decloud.common.entity.ProductEntity
import id.decloud.common.ui.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    application: Application,
    productEntity: ProductEntity
): BaseViewModel(application) {
    var oneProductLiveData: LiveData<ProductEntity>? = null

//    fun getOne()
}