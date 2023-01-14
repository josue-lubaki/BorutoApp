package ca.josuelubaki.borutoapp.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import ca.josuelubaki.borutoapp.domain.repository.DataStoreOperations
import ca.josuelubaki.borutoapp.util.Constants.PREFERENCES_KEY
import ca.josuelubaki.borutoapp.util.Constants.PREFERENCES_NAME
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

val Context.dataStore : DataStore<Preferences> by preferencesDataStore(name = PREFERENCES_NAME)

class DataStoreOperationsImpl(context : Context) : DataStoreOperations {

    private val dataStore = context.dataStore

    // object contains all preferences keys
    private object PreferencesKeys {
        val onBoardingCompletedKey = booleanPreferencesKey(name = PREFERENCES_KEY)
    }

    override suspend fun saveOnBoardingState(completed: Boolean) {
        dataStore.edit { preferencesArray ->
            preferencesArray[PreferencesKeys.onBoardingCompletedKey] = completed
        }
    }

    override fun readOnBoardingState(): Flow<Boolean> {
        return dataStore.data.catch { exception ->
            if(exception is IOException){
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map { preferencesArray ->
            preferencesArray[PreferencesKeys.onBoardingCompletedKey] ?: false
        }
    }

}
