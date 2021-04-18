package breaking.bad.azdaki.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import breaking.bad.azdaki.data.models.breaking.AllCharactersInfoItem
import breaking.bad.azdaki.R
import breaking.bad.azdaki.base.BaseFragment
import breaking.bad.azdaki.base.BaseViewModel
import breaking.bad.azdaki.base.showDialog
import breaking.bad.azdaki.data.network.NetworkClient
import breaking.bad.azdaki.databinding.HometestactivityBinding
import breaking.bad.azdaki.ui.chardDetails.CardDetailsFragment
import breaking.bad.azdaki.ui.chardDetails.CardDetailsFragmentDirections
import breaking.bad.azdaki.ui.registration.RegistrationFragment.Companion.KEY_DATA
import breaking.bad.azdaki.utils.BreakingBadCharacterDecorator
import breaking.bad.azdaki.utils.LoadMoreListener
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class HomeFragment: BaseFragment() {



    private var binding: HometestactivityBinding? = null

    private val viewModel by viewModels<HomeViewModel>()

    override fun getViewModelInstance() = viewModel

    private val adapter = CardAdapter{
        val action = CardDetailsFragmentDirections.actionGlobalCardDetailsFragment(it)
        activity?.findNavController(R.id.mainContainer)?.navigate(action)
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = HometestactivityBinding.inflate(inflater,container,false)
        return binding?.root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layoutManager = GridLayoutManager(context, 2)
        layoutManager.spanSizeLookup = CardAdapter.LoaderSpanSizeLookup(adapter)
        binding?.apply {
            recyclerView.layoutManager = layoutManager
            recyclerView.adapter = adapter
            recyclerView.addItemDecoration(
                    BreakingBadCharacterDecorator(
                            itemHorizontalInsets = resources.getDimensionPixelSize(R.dimen.char_list_hor_inst),
                            itemHorizontalSpacing = resources.getDimensionPixelSize(R.dimen.char_list_hor_space),
                            itemVerticalInsets = resources.getDimensionPixelSize(R.dimen.char_list_ver_inst),
                            itemVerticalSpacing = resources.getDimensionPixelSize(R.dimen.char_list_vrt_space),
                    )
            )
            recyclerView.addOnScrollListener(LoadMoreListener() {
                viewModel.onScrollEndReached()
            })
            swipeToRefresh.setOnRefreshListener {
                viewModel.onRefresh()
            }
            viewModel.items.observe(viewLifecycleOwner) {
                adapter.cardList = it
            }
            viewModel.loadingMore.observe(viewLifecycleOwner) {
                adapter.loadingMore = it
                if (swipeToRefresh.isRefreshing && it) swipeToRefresh.isRefreshing = false
            }

        }
    }
}


























/*
override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    val layoutManager = GridLayoutManager(context, 2)

    layoutManager

    binding.recyclerView.layoutManager = layoutManager
    binding.recyclerView.adapter = adapter
    binding.recyclerView.addItemDecoration(
            BreakingBadCharacterDecorator(
                    itemHorizontalInsets = resources.getDimensionPixelOffset(R.dimen.char_list_hor_inst),
                    itemHorizontalSpacing = resources.getDimensionPixelOffset(R.dimen.char_list_hor_space),
                    itemVerticalInsets = resources.getDimensionPixelOffset(R.dimen.char_list_ver_inst),
                    itemVerticalSpacing = resources.getDimensionPixelOffset(R.dimen.char_list_hor_inst)
            )
    )
    binding.recyclerView.addOnScrollListener(LoadMoreListener(this::loadMore))
    binding.swipeToRefresh.setOnRefreshListener {
        if (adapter.loadingMore) binding.swipeToRefresh.isRefreshing = false
        cardList.clear()
        adapter.notifyDataSetChanged()
        page = 0
        noMoreData = false
        loadMore()
    }
    loadMore()
}*/
