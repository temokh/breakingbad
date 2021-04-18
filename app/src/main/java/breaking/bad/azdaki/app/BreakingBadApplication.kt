package breaking.bad.azdaki.app

import android.app.Application
import android.content.Context
import breaking.bad.azdaki.data.storage.DataStore

class BreakingBadApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        DataStore.initialize(getSharedPreferences("_sp_", Context.MODE_PRIVATE))
    }

}