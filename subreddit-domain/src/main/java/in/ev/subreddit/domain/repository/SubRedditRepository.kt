package `in`.ev.domain.repository

import `in`.ev.domain.model.Response
import `in`.ev.subreddit.domain.model.SubRedditPost
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

interface SubRedditRepository {
    suspend fun getSubRedditPosts(pageSize: Int): Flow<PagingData<SubRedditPost>>
}