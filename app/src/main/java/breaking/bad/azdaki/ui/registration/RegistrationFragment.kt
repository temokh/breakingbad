package breaking.bad.azdaki.ui.registration

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import breaking.bad.azdaki.R
import breaking.bad.azdaki.base.BaseFragment
import breaking.bad.azdaki.databinding.RegistrationScreenBinding

class RegistrationFragment: BaseFragment() {


    private var binding: RegistrationScreenBinding? = null

    private val viewModel by viewModels<RegistrationViewModel>()

    override fun getViewModelInstance() = viewModel


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = RegistrationScreenBinding.inflate(inflater, container,false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.backText?.setOnClickListener { activity?.onBackPressed() }
        binding?.imageArrow?.setOnClickListener { activity?.onBackPressed() }

        binding?.registrationButton?.setOnClickListener {
            viewModel.onRegister(
                name = binding?.usernameEditText?.text,
                username = binding?.editTextTextPersonName?.text,
                password = binding?.editTextPassword?.text,
                repeatPassword = binding?.editTextRepeatPassword?.text
            )
        }
        viewModel.validationError.observe(viewLifecycleOwner, this::showValidationError)
        viewModel.registrationComplete.observe(viewLifecycleOwner) {
            findNavController().popBackStack(R.id.loginFragment, true)
        }
    }

    private fun showValidationError(error: RegistrationViewModel.ValidationError) {
        binding?.apply {
            when (error) {
                RegistrationViewModel.ValidationError.EmptyUsername -> {
                    usernameEditText.error = getString(R.string.registration_error_empty_username)
                }
                RegistrationViewModel.ValidationError.EmptyName -> {
                    editTextTextPersonName.error = getString(R.string.registration_error_empty_username)
                }
                RegistrationViewModel.ValidationError.EmptyPassword -> {
                    editTextPassword.error = getString(R.string.registration_error_empty_password)
                }
                RegistrationViewModel.ValidationError.PasswordsNotMatching -> {
                    editTextRepeatPassword.error = getString(R.string.registration_error_passwords_not_matching)
                }
                RegistrationViewModel.ValidationError.None -> {
                    usernameEditText.error = null
                    editTextTextPersonName.error = null
                    editTextPassword.error = null
                    editTextRepeatPassword.error = null
                }
            }
        }

    }


    companion object{
        const val KEY_USERNAME = "key _username"
        const val KEY_DATA = "key_data"
    }



}






