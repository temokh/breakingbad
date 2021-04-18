package breaking.bad.azdaki.data.storage.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SavedCardIdEntity(
    @PrimaryKey
    val id: String
)