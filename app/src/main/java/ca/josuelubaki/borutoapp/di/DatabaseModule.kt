package ca.josuelubaki.borutoapp.di

import android.content.Context
import androidx.room.Room
import ca.josuelubaki.borutoapp.data.local.BorutoDatabase
import ca.josuelubaki.borutoapp.util.Constants.BORUTO_DATABASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context : Context) : BorutoDatabase{
        return Room.databaseBuilder(context, BorutoDatabase::class.java, BORUTO_DATABASE).build()
    }

}