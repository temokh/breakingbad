package breaking.bad.azdaki.ui.savedCards

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import breaking.bad.azdaki.R
import breaking.bad.azdaki.base.BaseFragment
import breaking.bad.azdaki.base.BaseViewModel
import breaking.bad.azdaki.databinding.SavedCardsScreenBinding
import breaking.bad.azdaki.ui.chardDetails.CardDetailsFragmentDirections
import breaking.bad.azdaki.ui.home.CardAdapter
import breaking.bad.azdaki.ui.login.LoginViewModel
import breaking.bad.azdaki.utils.observeEvent

class SavedCardsFragment: BaseFragment(){

    private val viewModel by viewModels<SavedCardsViewModel>()

    private val loginViewModel by activityViewModels<LoginViewModel>()

    override fun getViewModelInstance() = viewModel

    private var binding: SavedCardsScreenBinding? = null

    private var adapter = CardAdapter(){
        val action = CardDetailsFragmentDirections.actionGlobalCardDetailsFragment(it)
        activity?.findNavController(R.id.mainContainer)?.navigate(action)
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = SavedCardsScreenBinding.inflate(inflater,container,false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layoutManager = GridLayoutManager(context, 2)
        layoutManager.spanSizeLookup = CardAdapter.LoaderSpanSizeLookup(adapter)
        binding?.apply {
            recycleView.layoutManager = layoutManager
            recycleView.adapter = adapter

        }

        viewModel.requestLogin.observeEvent(viewLifecycleOwner){
            login()
        }

        loginViewModel.loginFlowFinished.observeEvent(viewLifecycleOwner){ loginSuccess ->
            if (loginSuccess)
                viewModel.getSavedCards()
            else
                findNavController().navigate(R.id.show_home)

        }
    }

    private fun login(){
        activity?.findNavController(R.id.mainContainer)?.navigate(R.id.login)
    }




}