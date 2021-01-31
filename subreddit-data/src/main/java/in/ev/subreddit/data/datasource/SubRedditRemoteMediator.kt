package `in`.ev.subreddit.data.datasource

import `in`.ev.data.model.EntityResultWrapper
import `in`.ev.subreddit.data.datasource.remote.SubRedditRemoteDataSource
import `in`.ev.subreddit.data.local.SubRedditDatabase
import `in`.ev.subreddit.data.local.SubRedditPostDao
import `in`.ev.subreddit.data.mappers.toRoomEntity
import `in`.ev.subreddit.data.model.local.TblSubReddit
import `in`.ev.subreddit.data.model.local.TblSubRedditRemoteKey
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.LoadType.*
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.vikas.paging3.repository.local.SubRedditKeyDao

@OptIn(ExperimentalPagingApi::class)
class SubRedditRemoteMediator (
    private val db: SubRedditDatabase,
    private val subRedditRemoteDataSource: SubRedditRemoteDataSource,
    private val postId: String
) : RemoteMediator<Int, TblSubReddit>() {
    private val redditPostDao: SubRedditPostDao = db.posts()
    private val paginationKeyDao: SubRedditKeyDao = db.remoteKeys()

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, TblSubReddit>
    ): MediatorResult {
        val loadKey = when (loadType) {
            REFRESH -> null
            PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
            APPEND -> {
                val paginationKey = db.withTransaction {
                    paginationKeyDao.paginationKey(postId)
                }
                if (paginationKey?.after == null) {
                    return MediatorResult.Success(endOfPaginationReached = true)
                }
                paginationKey.after
            }
        }
        val response = subRedditRemoteDataSource.getSubRedditPosts(
            state.config
                .initialLoadSize.toString(), after = loadKey ?: ""
        )

        return when (response) {
            is EntityResultWrapper.Success -> {
                val itemsToInsert = response.data?.data?.children?.map {
                    it.data.toRoomEntity(it.data)
                }

                db.withTransaction {
                    if (loadType == REFRESH) {
                        redditPostDao.deleteById(postId)
                        paginationKeyDao.deleteById(postId)
                    }
                    // TODO: 30/1/21
                    //check whether this key will work or not
                    paginationKeyDao.insert(
                        TblSubRedditRemoteKey(
                            postId, response.data?.data
                                ?.after
                        )
                    )
                    itemsToInsert?.let {
                        redditPostDao.insertAll(it)
                    }
                    MediatorResult.Success(endOfPaginationReached = itemsToInsert?.isEmpty() == true)
                }
            }
            is EntityResultWrapper.Error -> {
                MediatorResult.Error(Throwable(response.error.status_message))
            }
        }
        //return MediatorResult.Success(endOfPaginationReached = true)
    }
}
