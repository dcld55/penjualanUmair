package id.decloud.penjualan.fragment.login

import android.widget.Toast
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import id.decloud.common.ui.BaseFragment
import id.decloud.penjualan.R
import id.decloud.penjualan.databinding.LoginLayoutFragmentBinding
import id.decloud.penjualan.view_model.LoginViewModel
import id.decloud.common.entity.LoginEntity
import id.decloud.common.ui.AppResponse

@AndroidEntryPoint
class LoginFragment : BaseFragment<LoginViewModel, LoginLayoutFragmentBinding>() {
    override val vm: LoginViewModel by viewModels()
    override val layoutResourceId: Int = R.layout.login_layout_fragment

    override fun initBinding(binding: LoginLayoutFragmentBinding) {
        super.initBinding(binding)

        binding.loginButton.setOnClickListener() {
            if (binding.user.text.isNullOrBlank() || binding.password.text.isNullOrBlank()) {
                Toast.makeText(context, "Username dan Password harus di-Isi", Toast.LENGTH_SHORT)
                    .show()
            } else {
                vm.login(
                    LoginEntity(
                        binding.user.text.toString(),
                        binding.password.text.toString()
                    )
                )

            }
        }

        binding.regis.setOnClickListener() {
            vm.navigate(LoginFragmentDirections.toRegister())
        }

        vm.loginLiveData?.observe(viewLifecycleOwner) {
            when (it) {
                is AppResponse.Error -> {
                    Toast.makeText(context, "Login Gagal", Toast.LENGTH_SHORT).show()
                }
                is AppResponse.Success -> {
                    Toast.makeText(context, "Login Berhasil", Toast.LENGTH_SHORT).show()
                    vm.navigate(LoginFragmentDirections.toProduct())

                }
            }
        }
    }
}