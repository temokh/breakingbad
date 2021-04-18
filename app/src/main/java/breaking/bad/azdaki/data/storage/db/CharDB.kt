package breaking.bad.azdaki.data.storage.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import breaking.bad.azdaki.data.models.breaking.AllCharactersInfoItem
import breaking.bad.azdaki.data.storage.db.typeConvertors.StringListTypeConverter

@Database(entities = [AllCharactersInfoItem::class], version = 1)
@TypeConverters(StringListTypeConverter::class)
abstract class CharDB: RoomDatabase() {
    abstract fun getCardDAO():CardDao
}