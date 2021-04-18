package breaking.bad.azdaki.ui.profile

import android.os.Bundle
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import breaking.bad.azdaki.R
import breaking.bad.azdaki.base.*
import breaking.bad.azdaki.data.models.user.UserProfile
import breaking.bad.azdaki.data.network.NetworkClient
import breaking.bad.azdaki.data.storage.DataStore
import breaking.bad.azdaki.databinding.ProfileActivityBinding
import breaking.bad.azdaki.ui.dialogs.LanguagePickerBottom
import breaking.bad.azdaki.ui.login.LoginViewModel
import breaking.bad.azdaki.utils.observeEvent
import com.bumptech.glide.Glide
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException

class ProfileFragment: BaseFragment() {



    private var binding: ProfileActivityBinding? = null

    private val viewModel by viewModels<ProfileViewModel>()

    private val loginViewModel by activityViewModels<LoginViewModel>()

    override fun getViewModelInstance() = viewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): ConstraintLayout? {
        binding = ProfileActivityBinding.inflate(inflater, container, false  )
        return binding?.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.languageTextview?.setOnClickListener {
            val languagePickerBottomSheet = LanguagePickerBottom()
            languagePickerBottomSheet.show(childFragmentManager, "tag")
        }

        binding?.logoutTextview?.setOnClickListener {
            DataStore.authToken = null

        }

        viewModel.userProfile.observe(viewLifecycleOwner,this::showUserData)
        viewModel.loginRequired.observe(viewLifecycleOwner){
            activity?.findNavController(R.id.mainContainer)?.navigate(R.id.login)
        }
        loginViewModel.loginFlowFinished.observeEvent(viewLifecycleOwner){ loginSuccess ->
            if (loginSuccess)
                viewModel.getUserData()
            else
                findNavController().navigate(R.id.show_home)

        }
    }


    private fun showUserData(userProfile: UserProfile){
        binding?.userNameTextView?.text = userProfile.userName
        binding?.nameTextView?.text = userProfile.name
        Glide.with(this@ProfileFragment)
                .load(userProfile.imageUrl)
                .centerCrop()
                .into(binding?.profilePictureImageView!!)
    }
}

