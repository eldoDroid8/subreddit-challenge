package `in`.ev.subreddit.domain.usecase

import `in`.ev.subreddit.domain.model.SubRedditPost
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

interface GetRedditPostUsecase {
    suspend fun execute(postId: String, limit: Int): Flow<PagingData<SubRedditPost>>
}