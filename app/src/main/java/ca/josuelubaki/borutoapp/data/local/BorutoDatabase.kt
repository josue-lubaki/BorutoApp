package ca.josuelubaki.borutoapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ca.josuelubaki.borutoapp.data.local.dao.HeroDao
import ca.josuelubaki.borutoapp.data.local.dao.HeroRemoteKeyDao
import ca.josuelubaki.borutoapp.domain.model.Hero
import ca.josuelubaki.borutoapp.domain.model.HeroRemoteKey

@Database(entities = [Hero::class, HeroRemoteKey::class], version = 1, exportSchema = false)
@TypeConverters(DatabaseConverter::class)
abstract class BorutoDatabase : RoomDatabase() {
    abstract fun heroDao(): HeroDao
    abstract fun heroRemoteKeyDao(): HeroRemoteKeyDao
}