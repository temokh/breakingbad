package breaking.bad.azdaki.data.storage.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import breaking.bad.azdaki.data.models.breaking.AllCharactersInfoItem


@Dao
interface CardDao {


    @Query("select * from allcharactersinfoitem")
    suspend fun getALL():List<AllCharactersInfoItem>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(allCharactersInfoItem: AllCharactersInfoItem)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(allCharactersInfoItem: List<AllCharactersInfoItem>)


}