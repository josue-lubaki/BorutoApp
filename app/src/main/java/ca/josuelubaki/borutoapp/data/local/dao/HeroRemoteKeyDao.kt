package ca.josuelubaki.borutoapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ca.josuelubaki.borutoapp.domain.model.HeroRemoteKey

@Dao
interface HeroRemoteKeyDao {

    @Query("SELECT * FROM heroes_remote_key_table WHERE id = :id")
    suspend fun getRemoteKeyById(id : Int) : HeroRemoteKey

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRemoteKeys(remoteKeys : List<HeroRemoteKey>)

    @Query("DELETE FROM heroes_remote_key_table")
    suspend fun deleteAllRemoteKeys()


}