package id.decloud.penjualan.fragment.product

import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.paging.LoadState
import dagger.hilt.android.AndroidEntryPoint
import id.decloud.common.ui.BaseFragment
import id.decloud.penjualan.R
import id.decloud.penjualan.databinding.ProductListLayoutFragmentBinding
import id.decloud.penjualan.view_model.ProductViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProductFragment : BaseFragment<ProductViewModel, ProductListLayoutFragmentBinding>() {
    override val vm: ProductViewModel by viewModels()
    override val layoutResourceId: Int = R.layout.product_list_layout_fragment
    private var adapter = ProductAdapter({
        vm.navigate(
            ProductFragmentDirections.toDetail(it)
        )
    }) { cart ->

        vm.insertToCart(vm.userStore.value.orEmpty(), cart)
        vm.navigate(
            ProductFragmentDirections.toDummy()
        )

    }



    override fun initBinding(binding: ProductListLayoutFragmentBinding) {
        super.initBinding(binding)

        binding.productRecycler.adapter = adapter

        vm.tokenStore.observe(viewLifecycleOwner) {
            vm.getProduct(it)
        }

        vm.productLiveData.observe(viewLifecycleOwner) {
            CoroutineScope(Dispatchers.Main).launch {
                adapter.submitData(it)
            }
        }

        adapter.addLoadStateListener {
            if (it.refresh is LoadState.Loading && adapter.itemCount == 0) {
                binding.progress.visibility = View.VISIBLE
                binding.productRecycler.visibility = View.GONE
                binding.checkoutButton.visibility = View.GONE
            } else if (it.refresh is LoadState.Error && adapter.itemCount == 0) {
                Toast.makeText(context, "Failed to Load Data", Toast.LENGTH_SHORT).show()
            } else if (it.refresh is LoadState.NotLoading && adapter.itemCount == 0) {
                Toast.makeText(context, "Data Is Empty", Toast.LENGTH_SHORT).show()
            } else if (it.refresh is LoadState.NotLoading && adapter.itemCount != 0) {
                binding.progress.visibility = View.GONE
                binding.productRecycler.visibility = View.VISIBLE
                binding.checkoutButton.visibility = View.VISIBLE
            }
        }

        binding.checkoutButton.setOnClickListener{
            vm.navigate(ProductFragmentDirections.toCart())
        }

    }


}