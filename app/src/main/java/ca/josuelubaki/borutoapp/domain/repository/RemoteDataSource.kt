package ca.josuelubaki.borutoapp.domain.repository

import androidx.paging.PagingData
import ca.josuelubaki.borutoapp.domain.model.Hero
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {
    fun getAllHeroes() : Flow<PagingData<Hero>>
    fun searchHeroes(name : String) : Flow<PagingData<Hero>>
}