package id.decloud.penjualan.fragment.register

import android.widget.Toast
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import id.decloud.common.request.LoginRequest
import id.decloud.common.ui.BaseFragment
import id.decloud.penjualan.R
import id.decloud.penjualan.databinding.RegisterLayoutFragmentBinding
import id.decloud.penjualan.view_model.RegisterViewModel

@AndroidEntryPoint
class RegisterFragment : BaseFragment<RegisterViewModel, RegisterLayoutFragmentBinding>() {
    override val vm: RegisterViewModel by viewModels()
    override val layoutResourceId: Int = R.layout.register_layout_fragment

    override fun initBinding(binding: RegisterLayoutFragmentBinding) {
        super.initBinding(binding)

        binding.loginButton.setOnClickListener() {
            if (binding.user.text.isNullOrBlank() || binding.password.text.isNullOrBlank()) {
                Toast.makeText(context, "Username dan Password harus di-Isi", Toast.LENGTH_SHORT)
                    .show()
            } else {
                vm.getRegister(
                    LoginRequest(
                        binding.user.text.toString(),
                        binding.password.text.toString()
                    )
                )
                Toast.makeText(context, "akun berhasil dibuat", Toast.LENGTH_SHORT).show()
                vm.popBackStack()
            }
        }
    }
}