package breaking.bad.azdaki.utils

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class LoadMoreListener(val callback:() -> Unit ) :RecyclerView.OnScrollListener()  {

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val gLayoutManager = (recyclerView.layoutManager as GridLayoutManager)
            if (recyclerView.adapter?.itemCount == gLayoutManager.findFirstCompletelyVisibleItemPosition() + 1){
                callback
            }

        }
    }