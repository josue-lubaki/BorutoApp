package ca.josuelubaki.borutoapp.data.paging_source

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingConfig
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.test.core.app.ApplicationProvider
import ca.josuelubaki.borutoapp.data.local.BorutoDatabase
import ca.josuelubaki.borutoapp.data.remote.FakeBorutoApi2
import ca.josuelubaki.borutoapp.domain.model.Hero
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class, ExperimentalPagingApi::class)
class HeroRemoteMediatorTest {

    private lateinit var borutoApi : FakeBorutoApi2
    private lateinit var borutoDatabase: BorutoDatabase

    @Before
    fun setup(){
        borutoApi = FakeBorutoApi2()
        borutoDatabase = BorutoDatabase.create(
            context = ApplicationProvider.getApplicationContext(),
            useInMemory = true
        )
    }

    @After
    fun cleanup(){
        borutoDatabase.clearAllTables()
    }

    @Test
    fun refreshLoadReturnsSuccessResultWhenMoreDataIsPresent(){
        runTest {
            val heroRemoteMediator = HeroRemoteMediator(
                borutoApi = borutoApi,
                borutoDatabase = borutoDatabase
            )

            val pagingState = PagingState<Int, Hero>(
                pages = listOf(),
                anchorPosition = null,
                config = PagingConfig(
                    pageSize = 3,
                ),
                leadingPlaceholderCount = 0
            )

            val loadResult = heroRemoteMediator.load(
                loadType = LoadType.REFRESH,
                state = pagingState
            )

            assertTrue(loadResult is RemoteMediator.MediatorResult.Success)
            assertFalse((loadResult as RemoteMediator.MediatorResult.Success).endOfPaginationReached)

        }
    }

    @Test
    fun refreshLoadSuccessAndEndOfPaginationTrueWhenNoMoreData(){
        runTest {
            borutoApi.clearData()
            val heroRemoteMediator = HeroRemoteMediator(
                borutoApi = borutoApi,
                borutoDatabase = borutoDatabase
            )

            val pagingState = PagingState<Int, Hero>(
                pages = listOf(),
                anchorPosition = null,
                config = PagingConfig(
                    pageSize = 3,
                ),
                leadingPlaceholderCount = 0
            )

            val loadResult = heroRemoteMediator.load(
                loadType = LoadType.REFRESH,
                state = pagingState
            )

            assertTrue(loadResult is RemoteMediator.MediatorResult.Success)
            assertTrue((loadResult as RemoteMediator.MediatorResult.Success).endOfPaginationReached)

        }
    }

    @Test
    fun refreshLoadReturnsErrorResultWhenErrorOccurs(){
        runTest {
            borutoApi.addException()
            val heroRemoteMediator = HeroRemoteMediator(
                borutoApi = borutoApi,
                borutoDatabase = borutoDatabase
            )

            val pagingState = PagingState<Int, Hero>(
                pages = listOf(),
                anchorPosition = null,
                config = PagingConfig(
                    pageSize = 3,
                ),
                leadingPlaceholderCount = 0
            )

            val loadResult = heroRemoteMediator.load(
                loadType = LoadType.REFRESH,
                state = pagingState
            )

            assertTrue(loadResult is RemoteMediator.MediatorResult.Error)

        }
    }

}