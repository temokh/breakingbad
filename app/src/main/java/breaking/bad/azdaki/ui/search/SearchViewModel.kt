package breaking.bad.azdaki.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import breaking.bad.azdaki.data.models.breaking.AllCharactersInfoItem
import breaking.bad.azdaki.data.network.NetworkClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class SearchViewModel: ViewModel() {


    private val _cards = MutableLiveData<List<AllCharactersInfoItem>>()
    val cards: LiveData<List<AllCharactersInfoItem>> get() = _cards

    val message = MutableLiveData<String>()

    fun onSearchTextChange(string: CharSequence?) {
        if (string.isNullOrEmpty()) _cards.postValue(emptyList())
        if (string?.length ?: 0 < 3) return
        viewModelScope.launch {
            launch(Dispatchers.IO) {
                try {
                    val cards = NetworkClient.breakingService.getChars("$string")
                    _cards.postValue(cards)
                    message.postValue("Count ${cards.size}")
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }



}