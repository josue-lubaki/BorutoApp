package ca.josuelubaki.borutoapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import ca.josuelubaki.borutoapp.util.Constants.HEROES_REMOTE_KEY_DATABASE_TABLE

@Entity(tableName = HEROES_REMOTE_KEY_DATABASE_TABLE)
data class HeroRemoteKey(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val prevPage: Int?,
    val nextPage: Int?,
)
