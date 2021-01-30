package `in`.ev.subreddit.data.repository.local

import `in`.ev.domain.repository.SubRedditPostsRepository
import `in`.ev.subreddit.data.datasource.SubRedditRemoteMediator
import `in`.ev.subreddit.data.datasource.remote.SubRedditRemoteDataSource
import `in`.ev.subreddit.data.local.SubRedditDatabase
import `in`.ev.subreddit.data.model.local.TblSubReddit
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RedditDbRepositoryImpl @Inject constructor(
    private val db: SubRedditDatabase, private val remoteDataSource:
    SubRedditRemoteDataSource
) : SubRedditPostsRepository<Flow<PagingData<TblSubReddit>>> {

    @ExperimentalPagingApi
    override fun getSubRedditPosts(
        postId: String,
        pageSize: Int
    ): Flow<PagingData<TblSubReddit>> {
        return Pager(
            config = PagingConfig(pageSize),
            remoteMediator = SubRedditRemoteMediator(db, remoteDataSource, postId),
            pagingSourceFactory = { db.posts().getPostsById(postId) }).flow
    }
}