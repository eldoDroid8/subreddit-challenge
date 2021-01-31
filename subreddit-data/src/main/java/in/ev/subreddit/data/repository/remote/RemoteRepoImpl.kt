package `in`.ev.subreddit.data.repository.remote

import `in`.ev.domain.repository.SubRedditPostsRepository
import `in`.ev.subreddit.data.datasource.remote.SubRedditRemoteDataSource
import `in`.ev.subreddit.data.datasource.remote.SubredditPagingSource
import `in`.ev.subreddit.data.mappers.toDomain
import `in`.ev.subreddit.domain.model.SubRedditPost
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RemoteRepoImpl @Inject constructor(private val remoteDataSource: SubRedditRemoteDataSource):
    SubRedditPostsRepository {
    override fun getSubRedditPosts(postId: String, pageSize: Int)= Pager(
        PagingConfig(pageSize)
    ) {
        SubredditPagingSource(
           subRedditRemoteDataSource = remoteDataSource
        )
    }.flow.flatMapMerge {
        flow {
            emit(it.map { it.data.toDomain(it.data) })
        }
    }
}