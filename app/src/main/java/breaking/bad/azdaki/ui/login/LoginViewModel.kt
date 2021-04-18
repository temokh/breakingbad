package breaking.bad.azdaki.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import breaking.bad.azdaki.R
import breaking.bad.azdaki.base.BaseViewModel
import breaking.bad.azdaki.data.network.NetworkClient
import breaking.bad.azdaki.data.storage.DataStore
import breaking.bad.azdaki.utils.Event
import breaking.bad.azdaki.utils.handleNetworkError
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class  LoginViewModel : BaseViewModel() {

    private val _inputError = MutableLiveData<Int>()
    val inputError: LiveData<Int> get() = _inputError

    private val _loginSuccess = MutableLiveData<Event<Unit>>()
    val loginSuccess: LiveData<Event<Unit>> get() = _loginSuccess

    private val _loginFlowFinished = MutableLiveData<Event<Boolean>>()
    val loginFlowFinished: LiveData<Event<Boolean>> get() = _loginFlowFinished


    fun login(username: CharSequence?, password: CharSequence?) = viewModelScope.launch {
        DataStore.authToken = null
        if (username.isNullOrBlank() || password.isNullOrBlank()) {
            _inputError.postValue(R.string.login_enter_username_password)
            return@launch
        }
        showLoading()
        try {
            val response = withContext(Dispatchers.IO) {
                NetworkClient.userService.login(
                    username = username.toString(),
                    password = password.toString()
                )
            }
            DataStore.authToken = response.accessToken
            _loginSuccess.postValue(Event(Unit))
        } catch (e: Exception) {
            handleNetworkError(e)
        } finally {
            hideLoading()
        }
    }


    fun fragmentDestroyed() {
        _loginFlowFinished.postValue(Event(!DataStore.authToken.isNullOrBlank()))
    }

    override fun onUnauthorized() {
        _inputError.postValue(R.string.login_screen_wrong_credentials)
    }


}