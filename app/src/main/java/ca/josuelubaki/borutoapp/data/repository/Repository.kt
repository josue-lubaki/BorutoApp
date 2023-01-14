package ca.josuelubaki.borutoapp.data.repository

import ca.josuelubaki.borutoapp.domain.repository.DataStoreOperations
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Repository @Inject constructor(
    private val dataStore : DataStoreOperations
){
    suspend fun onSaveBoardingState(completed : Boolean){
        dataStore.saveOnBoardingState(completed)
    }

    fun onReadOnBoardingState() : Flow<Boolean> {
        return dataStore.readOnBoardingState()
    }
}