package com.myapps.pixabayeye.data.test

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingConfig
import androidx.paging.PagingState
import androidx.paging.RemoteMediator.MediatorResult
import com.myapps.pixabayeye.data.database.AppDatabase
import com.myapps.pixabayeye.data.database.model.HitEntity
import com.myapps.pixabayeye.data.datasource.ImagesRemoteMediator
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlin.test.AfterTest
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

@ExperimentalPagingApi
@ExperimentalCoroutinesApi
class ImageRemoteMediatorTest {

    private val mockApi = SubNetworkApi()
    private val mockDb: AppDatabase = SubDatabaseFactory.create()

    @Test
    fun refreshLoadReturnsSuccessResult() {
        runTest {
            val mediator = ImagesRemoteMediator(
                database = mockDb,
                mainNetworkApi = mockApi,
                query = QUERY_ONE
            )
            val pagingState = PagingState<Int, HitEntity>(
                pages = listOf(),
                null,
                PagingConfig(PAGE_SIZE),
                PLACEHOLDER_COUNT
            )

            val result = mediator.load(LoadType.REFRESH, pagingState)
            assertTrue { result is MediatorResult.Success }
            assertFalse { (result as MediatorResult.Success).endOfPaginationReached }
        }
    }

    @AfterTest
    fun cleanAll() {
        mockDb.clearAllTables()
    }

    companion object {
        private const val PAGE_SIZE = 10
        private const val PLACEHOLDER_COUNT = 10
        private const val QUERY_ONE = "fruits"
    }
}
