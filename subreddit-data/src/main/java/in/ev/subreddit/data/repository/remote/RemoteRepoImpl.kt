package `in`.ev.subreddit.data.repository.remote

import `in`.ev.subreddit.domain.repository.SubRedditRepository
import `in`.ev.subreddit.data.datasource.remote.SubRedditRemoteDataSource
import `in`.ev.subreddit.data.datasource.remote.SubredditPagingSource
import `in`.ev.subreddit.data.mappers.toDomain
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.map
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RemoteRepoImpl @Inject constructor(private val remoteDataSource: SubRedditRemoteDataSource) :
    SubRedditRepository {
    @OptIn(FlowPreview::class)
    override suspend fun getSubRedditPosts(pageSize: Int) = Pager(PagingConfig(pageSize)) {
        SubredditPagingSource(
            subRedditRemoteDataSource = remoteDataSource
        )
    }.flow.flatMapMerge {
        flow {
            emit(it.map { toDomain(it.data) })
        }
    }
}