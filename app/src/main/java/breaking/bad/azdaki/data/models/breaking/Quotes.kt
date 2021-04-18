package breaking.bad.azdaki.data.models.breaking


import android.os.Parcelable
import com.squareup.moshi.Json
import androidx.annotation.Keep
import androidx.room.Entity
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
@Entity
data class Quotes(
    @Json(name = "author")
    val author: String,
    @Json(name = "quote")
    val quote: String,
    @Json(name = "quote_id")
    val quoteId: Int,
    @Json(name = "series")
    val series: String
):Parcelable