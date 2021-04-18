package breaking.bad.azdaki.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import breaking.bad.azdaki.data.models.breaking.AllCharactersInfoItem
import breaking.bad.azdaki.data.models.breaking.Quotes
import breaking.bad.azdaki.databinding.BreakCharItemBinding
import breaking.bad.azdaki.databinding.LoadingItemBinding
import com.bumptech.glide.Glide
import java.lang.RuntimeException


class CardAdapter(private val onItemClick: (
        characterCard: AllCharactersInfoItem) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var cardList: List<AllCharactersInfoItem> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }



    var loadingMore = false
        set(value) {
            field = value
            notifyItemChanged(itemCount - 1)
        }

    private val onClickListener = View.OnClickListener { v ->
        val card = v?.tag as AllCharactersInfoItem
        onItemClick.invoke(card)
    }

    override fun getItemViewType(position: Int): Int {
        return if (itemCount - 1 == position) VIEW_TYPE_LOADER else VIEW_TYPE_CARD
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            when (viewType) {
                VIEW_TYPE_LOADER -> LoadingViewHolder(
                        LoadingItemBinding.inflate(LayoutInflater.from(parent.context))
                )
                VIEW_TYPE_CARD -> CardViewHolder(
                        binding = BreakCharItemBinding.inflate(LayoutInflater.from(parent.context)),
                        onClickListener = onClickListener
                )
                else -> throw RuntimeException("unknown ViewType")
            }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is CardViewHolder -> {
                val item = cardList[position]
                holder.binding.characterName.text = item.name
                Glide.with(holder.itemView).load(item.img)
                        .into(holder.binding.characterImage)
                holder.binding.root.tag = item
            }
            is LoadingViewHolder -> {
                holder.binding.loader.visibility = if (loadingMore) View.VISIBLE else View.GONE
            }
        }
    }

    override fun getItemCount() = cardList.size + 1


    class CardViewHolder(
            val binding: BreakCharItemBinding,
            onClickListener: View.OnClickListener
    ) :
            RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener(onClickListener)
        }
    }

    class LoadingViewHolder(
            val binding: LoadingItemBinding
    ) : RecyclerView.ViewHolder(binding.root)

    class LoaderSpanSizeLookup(val adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>) :
            GridLayoutManager.SpanSizeLookup() {
        override fun getSpanSize(position: Int): Int {
            return if (adapter.itemCount - 1 == position) 2 else 1
        }
    }


    companion object {
        const val VIEW_TYPE_CARD = 1
        const val VIEW_TYPE_LOADER = 2
    }

}




























































/*
class CardAdapter(private val onItemClick: (allCharItem: AllCharactersInfoItem) -> Unit): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var cardList: List<AllCharactersInfoItem> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var loadingMore = false
        set(value) {
            field = value
            notifyItemChanged(itemCount - 1)
        }


    private val onClickListener = View.OnClickListener { v ->
        val card = v?.tag as AllCharactersInfoItem
        onItemClick.invoke(card)

    }


    override fun getItemViewType(position: Int): Int {
        return if (itemCount - 1 == position) HomeFragment.VIEW_TYPE_LOADER else HomeFragment.VIEW_TYPE_CARD
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            when (viewType) {
                HomeFragment.VIEW_TYPE_LOADER -> CardViewHolder.LoadingViewHolder(
                        LoadingItemBinding.inflate(LayoutInflater.from(parent.context))
                )

                HomeFragment.VIEW_TYPE_CARD -> CardViewHolder(
                        binding = BreakCharItemBinding.inflate(LayoutInflater.from(parent.context)),
                        onClickListener = onClickListener
                )

                else -> throw RuntimeException("Unknown View Type")
            }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when (holder) {


            is CardViewHolder -> {
                val item = cardList.get(position)
                holder.binding.characterName.text = item.name
                Glide.with(holder.itemView).load(item.img).into(holder.binding.characterImage)
                holder.binding.root.tag = item
            }

            is CardViewHolder.LoadingViewHolder -> {
                holder.binding.loader.visibility = if (loadingMore) View.VISIBLE else View.GONE
            }

        }


    }

    override fun getItemCount() = cardList.size + 1


    class CardViewHolder(
            val binding: BreakCharItemBinding,
            onClickListener: View.OnClickListener
    ) :
            RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener(onClickListener)
        }


        class LoadingViewHolder(
                val binding: LoadingItemBinding
        ) : RecyclerView.ViewHolder(binding.root)


        class LoaderSpanSizeLookup(val adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>) :
                GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return if (adapter.itemCount - 1 == position) 2 else 1
            }
        }

        companion object {
            const val VIEW_TYPE_CARD = 1
            const val VIEW_TYPE_LOADER = 2
        }

    }

}
*/


