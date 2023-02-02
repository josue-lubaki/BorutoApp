package ca.josuelubaki.borutoapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import ca.josuelubaki.borutoapp.util.Constants.HEROES_REMOTE_KEYS_DATABASE_TABLE

@Entity(tableName = HEROES_REMOTE_KEYS_DATABASE_TABLE)
data class HeroRemoteKeys(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val prevPage: Int?,
    val nextPage: Int?,
    val lastUpdated: Long?,
)
