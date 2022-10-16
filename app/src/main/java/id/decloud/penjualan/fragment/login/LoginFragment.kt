package id.decloud.penjualan.fragment.login

import android.widget.Toast
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.qualifiers.ApplicationContext
import id.decloud.api_service.data_source.DataStorePreference
import id.decloud.common.request.LoginRequest
import id.decloud.common.ui.BaseFragment
import id.decloud.penjualan.R
import id.decloud.penjualan.databinding.LoginLayoutFragmentBinding
import id.decloud.penjualan.view_model.LoginViewModel

@AndroidEntryPoint
class LoginFragment : BaseFragment<LoginViewModel, LoginLayoutFragmentBinding>() {
    override val vm: LoginViewModel by viewModels()
    override val layoutResourceId: Int = R.layout.login_layout_fragment


    override fun initBinding(binding: LoginLayoutFragmentBinding) {
        super.initBinding(binding)


        binding.loginButton.setOnClickListener {
            if (binding.user.text.isNullOrBlank() || binding.password.text.isNullOrBlank()) {
                Toast.makeText(context, "Username dan Password harus di-Isi", Toast.LENGTH_SHORT)
                    .show()
            } else {
                vm.login(
                    LoginRequest(
                        binding.user.text.toString(),
                        binding.password.text.toString()
                    )
                )

            }
        }

        binding.regis.setOnClickListener {
            vm.navigate(LoginFragmentDirections.toRegister())
        }

        observeResponseData(vm.loginLiveData,
            success = {
                vm.navigate(LoginFragmentDirections.toProduct()
                )
                      },
            error = {Toast.makeText(context, "Login Gagal", Toast.LENGTH_SHORT).show()},
            loading = {Toast.makeText(context, "Loading", Toast.LENGTH_SHORT).show()}
        )


//        vm.loginLiveData.observe(viewLifecycleOwner) {
//            when (it) {
//                is AppResponse.Error -> {
//                    Toast.makeText(context, "Login Gagal", Toast.LENGTH_SHORT).show()
//                }
//                is AppResponse.Success -> {
//                    Toast.makeText(context, "Login Berhasil", Toast.LENGTH_SHORT).show()
////                    vm.navigate(LoginFragmentDirections.toProduct())
//
//                }
//            }
//        }
    }
}