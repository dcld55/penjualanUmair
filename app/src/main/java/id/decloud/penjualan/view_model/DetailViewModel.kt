package id.decloud.penjualan.view_model

import android.app.Application
import dagger.hilt.android.lifecycle.HiltViewModel
import id.decloud.common.ui.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    application: Application,

): BaseViewModel(application) {

}