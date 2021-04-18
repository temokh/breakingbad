package breaking.bad.azdaki.data.storage.db.typeConvertors

import androidx.room.TypeConverter

class StringListTypeConverter {

    @TypeConverter
    fun toStringList(string: String?):List<String>?{
        return string?.split("|")?.toList()
    }

    @TypeConverter
    fun listtoString(string: List<String>?):String?{
        return string?.joinToString { "|" }
    }








}

