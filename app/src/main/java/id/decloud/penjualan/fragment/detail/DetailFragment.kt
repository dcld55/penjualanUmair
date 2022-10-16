package id.decloud.penjualan.fragment.detail

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import id.decloud.common.ui.BaseFragment
import id.decloud.penjualan.R
import id.decloud.penjualan.databinding.DetailProductLayoutFragmentBinding
import id.decloud.penjualan.view_model.DetailViewModel

@AndroidEntryPoint
class DetailFragment : BaseFragment<DetailViewModel, DetailProductLayoutFragmentBinding>() {
    override val vm: DetailViewModel by viewModels()
    override val layoutResourceId: Int = R.layout.detail_product_layout_fragment
    private val args by navArgs<DetailFragmentArgs>()

    override fun initBinding(binding: DetailProductLayoutFragmentBinding) {
        super.initBinding(binding)

        binding.dimensionContent.text = args.oneContent.dimension
        binding.priceContent.text = args.oneContent.price.toString()
        binding.title.text = args.oneContent.name
        binding.priceUnitContent.text = args.oneContent.unit

    }

}