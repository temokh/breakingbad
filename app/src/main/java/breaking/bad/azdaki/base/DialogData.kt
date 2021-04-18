package breaking.bad.azdaki.base
import androidx.annotation.StringRes

data class DialogData(
        @StringRes val title: Int,
        @StringRes val messageRes: Int? = null,
        val message: String? = null
)
