package breaking.bad.azdaki.data.storage

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import androidx.room.RoomDatabase
import breaking.bad.azdaki.data.storage.db.CharDB
import java.lang.RuntimeException
import java.util.*

object DataStore {
    const val KEY_LANGUAGE = "key_language"
    const val KEY_TOKEN = "key_token"
    private var sharedPreferences: SharedPreferences? = null

    private var dataBase: CharDB? = null
    val db get() = dataBase?: throw RuntimeException("not initialized!!")

    fun initialize(context : Context,sharedPreferences: SharedPreferences){
        DataStore.sharedPreferences = sharedPreferences
        dataBase = Room.databaseBuilder(context, CharDB::class.java,"chardb").build()
    }

     var language:String
    @SuppressLint("ApplySharedPref")
    set(value) {
        sharedPreferences?.edit()?.putString(KEY_LANGUAGE,value)?.commit()
    }

    get() {
        return sharedPreferences?.getString(KEY_LANGUAGE, Locale.getDefault().language)
            ?:throw RuntimeException("not initialized")
    }



    var authToken: String?
        @SuppressLint("ApplySharedPref")
        set(value) {
            sharedPreferences?.edit()?.putString(KEY_TOKEN,value)?.commit()
        }

        get() {
            return (sharedPreferences ?: throw RuntimeException("not initialized!!"))
                    .getString(KEY_TOKEN, null)
        }

}