package breaking.bad.azdaki.ui.savedCards

import android.util.EventLog
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import breaking.bad.azdaki.base.BaseViewModel
import breaking.bad.azdaki.data.network.NetworkClient
import breaking.bad.azdaki.utils.Event
import breaking.bad.azdaki.utils.handleNetworkError
import kotlinx.coroutines.launch
import java.lang.Exception

class SavedCardsViewModel : BaseViewModel() {

    private val _requestLogin = MutableLiveData<Event<Unit>>()
    val requestLogin: LiveData<Event<Unit>> get() = _requestLogin

    init {
        getSavedCards()
    }

    fun getSavedCards() = viewModelScope.launch {
        try {
            showLoading()
            val cards = NetworkClient.userService.getUserCards()
        }catch (e: Exception){

            handleNetworkError(e)
        }finally {
            hideLoading()
        }
    }

    override fun onUnauthorized() {
        _requestLogin.postValue(Event(Unit))
    }





}