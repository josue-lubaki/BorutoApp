package ca.josuelubaki.borutoapp.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import ca.josuelubaki.borutoapp.data.local.BorutoDatabase
import ca.josuelubaki.borutoapp.data.paging_source.HeroRemoteMediator
import ca.josuelubaki.borutoapp.data.remote.BorutoApi
import ca.josuelubaki.borutoapp.domain.model.Hero
import ca.josuelubaki.borutoapp.domain.repository.RemoteDataSource
import ca.josuelubaki.borutoapp.util.Constants.ITEMS_PER_PAGE
import kotlinx.coroutines.flow.Flow

class RemoteDataSourceImpl(
    private val borutoApi: BorutoApi,
    private val borutoDatabase: BorutoDatabase
) : RemoteDataSource {
    private val heroDao = borutoDatabase.heroDao()

    @OptIn(ExperimentalPagingApi::class)
    override fun getAllHeroes(): Flow<PagingData<Hero>> {
        val pagingSourceFactory = { heroDao.getAllHeroes() }
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            remoteMediator = HeroRemoteMediator(
                borutoApi,
                borutoDatabase
            ),
            pagingSourceFactory = pagingSourceFactory,
        ).flow
    }

    override fun searchHeroes(name: String): Flow<PagingData<Hero>> {
        TODO("Not yet implemented")
    }

}