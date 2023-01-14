package ca.josuelubaki.borutoapp.di

import android.content.Context
import ca.josuelubaki.borutoapp.data.pref.DataStoreOperationsImpl
import ca.josuelubaki.borutoapp.domain.repository.DataStoreOperations
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideDataStoreOperations(@ApplicationContext context : Context) : DataStoreOperations {
        return DataStoreOperationsImpl(context = context)
    }
}