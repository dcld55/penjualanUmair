package id.decloud.penjualan.fragment.checkout

import android.widget.Toast
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import id.decloud.common.Count
import id.decloud.common.ui.BaseFragment
import id.decloud.penjualan.R
import id.decloud.penjualan.databinding.CheckoutLayoutFragmentBinding
import id.decloud.penjualan.view_model.CheckoutViewModel

@AndroidEntryPoint
class CheckoutFragment : BaseFragment<CheckoutViewModel, CheckoutLayoutFragmentBinding>() {
    override val vm: CheckoutViewModel by viewModels()
    override val layoutResourceId: Int = R.layout.checkout_layout_fragment
    private val adapter = CheckoutAdapter() {
        vm.subTotal[it.productCode] = it
        val total = vm.subTotal.map {
            Count.getSubTotal(it.value)
        }.sum()
        binding.totalPriceText.text = total.toString()
    }

    override fun initBinding(binding: CheckoutLayoutFragmentBinding) {
        super.initBinding(binding)
        binding.checkoutRecycler.adapter = adapter


        binding.checkoutButton.setOnClickListener {
            vm.insertIntoTransaction(vm.user.value.orEmpty())
            vm.deleteCart()
            Toast.makeText(context, "Processing your purchase, thank you", 2).show()
            vm.popBackStack()
        }

        vm.cartLiveData?.observe(viewLifecycleOwner) {
            adapter.differ.submitList(it)
            it.forEach { cart ->
                vm.subTotal[cart.productCode] = cart
            }
            var total = 0.0
            vm.subTotal.forEach {
                total += it.value.quantity * it.value.price
            }
            binding.totalPriceText.text = total.toString()
        }
    }
}