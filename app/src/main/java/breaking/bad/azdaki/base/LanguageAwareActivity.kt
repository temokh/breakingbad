package breaking.bad.azdaki.base


import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.ContentFrameLayout
import breaking.bad.azdaki.R
import breaking.bad.azdaki.data.storage.DataStore
import breaking.bad.azdaki.utils.updateLocale


abstract class LanguageAwareActivity : AppCompatActivity() {

    private lateinit var loadingView: View

    private lateinit var contentView: ContentFrameLayout

    private var loading = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        contentView = findViewById<ContentFrameLayout>(android.R.id.content)
        loadingView = layoutInflater.inflate(R.layout.dialog_loading, contentView, false)
    }

    override fun attachBaseContext(newBase: Context?) {
        val newLangContext = newBase?.let { updateLocale(it, DataStore.language) }
        super.attachBaseContext(newLangContext)
    }

    protected fun showDialog(@StringRes title: Int, @StringRes message: Int) {
        showDialog(title, getString(message))
    }

    protected fun showDialog(@StringRes title: Int, message: String) {
        AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
                .setNeutralButton(
                        R.string.common_Ok
                ) { dialog, _ -> dialog.dismiss() }
                .setCancelable(true)
                .show()
    }

    fun showLoading() {
        if (loading) return
        contentView.addView(loadingView)
        loading = true
    }

    fun hideLoading() {
        if (!loading) return
        contentView.removeView(loadingView)
        loading = false
    }


}