package breaking.bad.azdaki.ui.home
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import breaking.bad.azdaki.R
import breaking.bad.azdaki.base.BaseViewModel
import breaking.bad.azdaki.base.DialogData
import breaking.bad.azdaki.data.models.breaking.AllCharactersInfoItem
import breaking.bad.azdaki.data.network.NetworkClient
import breaking.bad.azdaki.data.storage.DataStore

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel : BaseViewModel() {

    private val _items = MutableLiveData<List<AllCharactersInfoItem>>()
    val items: LiveData<List<AllCharactersInfoItem>> get() = _items

    private val _loadingMore = MutableLiveData(false)
    val loadingMore: LiveData<Boolean> get() = _loadingMore

    private var noMoreData = false

    private var page = 1

    init {
        loadMore()
    }

    fun onScrollEndReached() {
        if (loadingMore.value == true || noMoreData) return
        loadMore()
    }

    fun onRefresh() {
        page = 1
        _items.postValue(emptyList())
        loadMore()
    }

    private fun loadMore() {
        viewModelScope.launch {
            _loadingMore.value = true
            try {
                val data = withContext(Dispatchers.IO) {
                    NetworkClient.breakingService.getChars(
                            limit = 62,
                            offset = 0
                    )
                }
//                DataStore.db.getCardDAO().insert(data)
                _items.postValue((_items.value ?: emptyList()) + data)
            } catch (e: Exception) {
                showDialog(DialogData(title = R.string.common_error, message = e.message ?: ""))
            } finally {
                _loadingMore.value = false
            }
        }
    }


}