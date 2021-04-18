package breaking.bad.azdaki.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.*
import androidx.navigation.fragment.findNavController
import breaking.bad.azdaki.R
import breaking.bad.azdaki.ui.registration.RegistrationFragment
import breaking.bad.azdaki.base.BaseFragment
import breaking.bad.azdaki.databinding.LoginScreenBinding

class LoginFragment : BaseFragment(), View.OnClickListener {

    private var binding: LoginScreenBinding? = null

    private val viewModel: LoginViewModel by activityViewModels()

    override fun getViewModelInstance() = viewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFragmentResultListener(RegistrationFragment.KEY_DATA){ _, bundle ->
            binding?.usernameEditText?.setText(bundle.getString(RegistrationFragment.KEY_USERNAME))
        }
    }
    
    

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = LoginScreenBinding.inflate(inflater, container, false)
        return binding?.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.registerTextView?.setOnClickListener(this)
        binding?.loginButton?.setOnClickListener(this)
        viewModel.inputError.observe(viewLifecycleOwner){
            binding?.passwordEditText?.error = getString(it)
        }
        viewModel.loginSuccess.observe(viewLifecycleOwner){

            findNavController().popBackStack()
        }


    }


    override fun onClick(v: View?) {
        when(v){
            binding?.registerTextView ->{
                startRegistration()
            }

            binding?.loginButton -> {
                viewModel.login(
                    username = binding?.usernameEditText?.text,
                    password = binding?.passwordEditText?.text
                )
            }
        }
    }

    override fun onDestroy() {
        viewModel.fragmentDestroyed()
        super.onDestroy()
    }



    private fun startRegistration(){

        findNavController().navigate(R.id.form_logint_to_registration  )

    }

    companion object {
        const val KEY_LOGIN_RESULT = "key_login_result"
        const val KEY_LOGIN_RESULT_SUCCESS = "key_login_result_success"
    }

}