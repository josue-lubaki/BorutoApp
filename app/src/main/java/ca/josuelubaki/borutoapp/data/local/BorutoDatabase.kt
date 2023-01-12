package ca.josuelubaki.borutoapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import ca.josuelubaki.borutoapp.data.local.dao.HeroDao
import ca.josuelubaki.borutoapp.data.local.dao.HeroRemoteKeyDao
import ca.josuelubaki.borutoapp.domain.model.Hero
import ca.josuelubaki.borutoapp.domain.model.HeroRemoteKey

@Database(entities = [Hero::class, HeroRemoteKey::class], version = 1, exportSchema = false)
abstract class BorutoDatabase : RoomDatabase() {
    abstract fun heroDao(): HeroDao
    abstract fun heroRemoteKeyDao(): HeroRemoteKeyDao
}