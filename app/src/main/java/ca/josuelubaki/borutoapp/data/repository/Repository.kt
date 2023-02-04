package ca.josuelubaki.borutoapp.data.repository

import androidx.paging.PagingData
import ca.josuelubaki.borutoapp.domain.model.Hero
import ca.josuelubaki.borutoapp.domain.repository.DataStoreOperations
import ca.josuelubaki.borutoapp.domain.repository.RemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Repository @Inject constructor(
    private val remote: RemoteDataSource,
    private val dataStore : DataStoreOperations
){
    fun getAllHeroes() : Flow<PagingData<Hero>> = remote.getAllHeroes()

    fun searchHeroes(name : String) : Flow<PagingData<Hero>> = remote.searchHeroes(name)

    suspend fun onSaveBoardingState(completed : Boolean){
        dataStore.saveOnBoardingState(completed)
    }

    fun onReadOnBoardingState() : Flow<Boolean> {
        return dataStore.readOnBoardingState()
    }
}