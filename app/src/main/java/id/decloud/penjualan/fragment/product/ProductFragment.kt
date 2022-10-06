package id.decloud.penjualan.fragment.product

import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import id.decloud.common.ui.BaseFragment
import id.decloud.penjualan.R
import id.decloud.penjualan.databinding.ProductListLayoutFragmentBinding
import id.decloud.penjualan.view_model.ProductViewModel

@AndroidEntryPoint
class ProductFragment : BaseFragment<ProductViewModel, ProductListLayoutFragmentBinding>() {
    override val vm: ProductViewModel by viewModels()
    override val layoutResourceId: Int = R.layout.product_list_layout_fragment
    private var adapter = ProductAdapter()

    override fun initBinding(binding: ProductListLayoutFragmentBinding) {
        super.initBinding(binding)

        binding.productRecycler.adapter = adapter

        vm.productLiveData?.observe(viewLifecycleOwner){
            adapter.differ.submitList(it)
        }
    }


}