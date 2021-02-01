package `in`.ev.subreddit.data.repository.local

import `in`.ev.domain.repository.SubRedditRepository
import `in`.ev.subreddit.data.datasource.SubRedditRemoteMediator
import `in`.ev.subreddit.data.datasource.remote.SubRedditRemoteDataSource
import `in`.ev.subreddit.data.local.SubRedditDatabase
import `in`.ev.subreddit.data.mappers.toDomain
import `in`.ev.subreddit.domain.model.SubRedditPost
import androidx.paging.*
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RedditDbRepositoryImpl @Inject constructor(
    private val db: SubRedditDatabase, private val remoteDataSource:
    SubRedditRemoteDataSource
) : SubRedditRepository {
    override suspend fun getSubRedditPosts(pageSize: Int): Flow<PagingData<SubRedditPost>> {
        TODO("Not yet implemented")
    }

    /*@ExperimentalPagingApi
    override fun getSubRedditPosts(
        postId: String,
        pageSize: Int
    ): Flow<PagingData<SubRedditPost>> {
        if (db == null) throw IllegalStateException("Database is not initialized")
        val pagingFlow =  Pager(
            config = PagingConfig(pageSize),
            remoteMediator = SubRedditRemoteMediator(db, remoteDataSource, postId),
            pagingSourceFactory = { db.posts().getPostsById(postId) }).flow
        val domainFlow = pagingFlow.flatMapMerge {
            flow {
              emit(it.map { it.toDomain(it) })
            }
        }
        return domainFlow.flowOn(Dispatchers.Default)
    }*/

  /*  @OptIn(ExperimentalPagingApi::class, FlowPreview::class)
    override fun getSubRedditPosts(
        pageSize: Int
    ) = Pager(
        config = PagingConfig(pageSize),
        remoteMediator = SubRedditRemoteMediator(db, remoteDataSource),
        pagingSourceFactory = { db.posts().getPostsById(postId) }).flow.flatMapMerge {
        flow {
            emit(it.map { it.toDomain(it) })
        }
    }*/
}