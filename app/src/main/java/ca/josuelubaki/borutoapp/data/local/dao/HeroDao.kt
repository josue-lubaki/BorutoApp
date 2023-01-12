package ca.josuelubaki.borutoapp.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ca.josuelubaki.borutoapp.domain.model.Hero

@Dao
interface HeroDao {
    @Query("SELECT * FROM heroes_table ORDER BY id ASC")
    fun getAllHeroes() : PagingSource<Int, Hero>

    @Query("SELECT * FROM heroes_table WHERE id = :id")
    fun getHeroById(id : Int) : Hero

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHeroes(heroes : List<Hero>)

    @Query("DELETE FROM heroes_table")
    suspend fun deleteAllHeroes()
}