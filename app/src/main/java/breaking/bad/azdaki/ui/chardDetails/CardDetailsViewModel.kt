package breaking.bad.azdaki.ui.chardDetails

import androidx.lifecycle.*
import breaking.bad.azdaki.base.BaseViewModel
import breaking.bad.azdaki.data.models.breaking.AllCharactersInfoItem
import breaking.bad.azdaki.data.models.breaking.Quotes
import breaking.bad.azdaki.data.network.NetworkClient
import breaking.bad.azdaki.utils.NetworkErrorHandler
import breaking.bad.azdaki.utils.handleNetworkError
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class CardDetailsViewModel(
        private val data: AllCharactersInfoItem
        ) : BaseViewModel() {

    private val  _cardModel = MutableLiveData(data)
     val cardModel: LiveData<AllCharactersInfoItem> get() = _cardModel


    private val _quoteModel = MutableLiveData<List<Quotes>>()
    val quoteModel: LiveData<List<Quotes>> get() = _quoteModel



    fun getQuotes(name: String){
        viewModelScope.launch(Dispatchers.IO){
            try {
                val quotes = NetworkClient.breakingService.getQuotes(name)
                quotes.map { i -> i.quote }
                _quoteModel.postValue(quotes)
            }catch (e: Exception){
                handleNetworkError(e)
            }
        }
    }








    @Suppress("UNCHECKED_CAST")
    class CardDetailsViewModelFactory(private val data: AllCharactersInfoItem) :
            ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return CardDetailsViewModel(data) as T
        }
    }

}