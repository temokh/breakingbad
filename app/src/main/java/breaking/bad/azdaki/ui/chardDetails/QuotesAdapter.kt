package breaking.bad.azdaki.ui.chardDetails

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import breaking.bad.azdaki.R
import breaking.bad.azdaki.data.models.breaking.Quotes
import breaking.bad.azdaki.databinding.QuoteItemsBinding
import breaking.bad.azdaki.ui.home.CardAdapter

class QuotesAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    var quoteList: List<Quotes> = emptyList()
    set(value) {
        field = value
        notifyDataSetChanged()
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return QuotesViewHolder(binding = QuoteItemsBinding.inflate(LayoutInflater.from(parent.context)))

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when(holder){
            is QuotesViewHolder -> {
                val item = quoteList[position]
                holder.binding.charQuote.text = item.quote
            }
        }



    }

    override fun getItemCount() = quoteList.size

    class QuotesViewHolder(val binding: QuoteItemsBinding): RecyclerView.ViewHolder(binding.root)




}