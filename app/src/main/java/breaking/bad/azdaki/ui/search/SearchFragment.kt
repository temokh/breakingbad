package breaking.bad.azdaki.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import breaking.bad.azdaki.data.models.breaking.AllCharactersInfoItem
import breaking.bad.azdaki.R
import breaking.bad.azdaki.databinding.SearchScreenBinding
import breaking.bad.azdaki.ui.chardDetails.CardDetailsFragmentDirections
import breaking.bad.azdaki.ui.home.CardAdapter
import breaking.bad.azdaki.utils.BreakingBadCharacterDecorator

class SearchFragment: Fragment() {

    val viewModel: SearchViewModel by viewModels()


    private var binding: SearchScreenBinding? = null


    private val adapter = CardAdapter() {
        val action = CardDetailsFragmentDirections.actionGlobalCardDetailsFragment(it)
        activity?.findNavController(R.id.mainContainer)
                ?.navigate(action)
    }


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = SearchScreenBinding.inflate(inflater,container,false)
        return binding?.root
    }




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layoutManager = GridLayoutManager(context, 2)
        layoutManager.spanSizeLookup = CardAdapter.LoaderSpanSizeLookup(adapter)

        binding?.recyclerView?.layoutManager = layoutManager
        binding?.recyclerView?.adapter = adapter
        binding?.recyclerView?.addItemDecoration(
                BreakingBadCharacterDecorator(
                        itemHorizontalInsets = resources.getDimensionPixelOffset(R.dimen.char_list_hor_inst),
                        itemHorizontalSpacing = resources.getDimensionPixelOffset(R.dimen.char_list_hor_space),
                        itemVerticalInsets = resources.getDimensionPixelOffset(R.dimen.char_list_ver_inst),
                        itemVerticalSpacing = resources.getDimensionPixelOffset(R.dimen.char_list_hor_inst)
                )
        )
        viewModel.cards.observe(viewLifecycleOwner){
                adapter.cardList = it
        }
        binding?.searchInput?.doOnTextChanged { text, _, _, _ ->
            viewModel.onSearchTextChange(text)
        }

        viewModel.message.observe(viewLifecycleOwner){
            Toast.makeText(context,it,Toast.LENGTH_SHORT).show()
        }

    }
/*    inner class LoaderSpanSizeLookup : GridLayoutManager.SpanSizeLookup(){
        override fun getSpanSize(position: Int): Int {
            return if (adapter.itemCount -1 == position) 2 else 1
        }
    }*/


}