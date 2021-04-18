package breaking.bad.azdaki.ui.chardDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import breaking.bad.azdaki.data.models.breaking.AllCharactersInfoItem
import breaking.bad.azdaki.data.models.breaking.Quotes
import breaking.bad.azdaki.databinding.CardDetailsScreenBinding
import breaking.bad.azdaki.databinding.QuoteItemsBinding
import com.bumptech.glide.Glide

class CardDetailsFragment: Fragment() {


    private var binding: CardDetailsScreenBinding? = null




    private val cardDetailArg by navArgs<CardDetailsFragmentArgs>()

    private val adapter = QuotesAdapter()




    private val viewModel by viewModels<CardDetailsViewModel> {
        CardDetailsViewModel.CardDetailsViewModelFactory(
                cardDetailArg.data
        )
    }





    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = CardDetailsScreenBinding.inflate(inflater,container,false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.cardModel.observe(viewLifecycleOwner) {
                showCardData(it)
                viewModel.getQuotes(it.name)
        }

        binding?.apply {
            val layoutManager = LinearLayoutManager(context)
            recycleView.adapter = adapter
            recycleView.layoutManager = layoutManager
            viewModel.quoteModel.observe(viewLifecycleOwner){
                adapter.quoteList = it
            }
        }





        binding?.backButton?.setOnClickListener {
            findNavController().popBackStack()
        }
        binding?.BackTextView?.setOnClickListener{
            findNavController().popBackStack()
        }
    }

    private fun showCardData(card: AllCharactersInfoItem){

        binding?.apply {
            Glide.with(imgFromServer).load(card.img).into(imgFromServer)
            NameTextView.text = getName(card.name, " ")
            surnameTextView.text = getSurname(card.name)
            nickNameTextView.text = card.nickname
            occupationFirstTextView.text = card.occupation[0]
            if (card.occupation.size > 1) occupationSecondTextView.text = card.occupation[1]
            birthDayTextView.text = card.birthday
            statusTextView.text = card.status
            protrayedTextView.text = card.portrayed


        }

    }





    fun getSurname(s:String) :String{
        return if (s.contains(" "))
            s.substring(s.indexOf(' '))
                    .trim { it <= ' ' } else ""
    }
    fun getName(s: String, separator: String): String {
        val index = s.indexOf(separator)
        return  if (index < 0) s else s.substring(0, index)
     }







}