package `in`.ev.subreddit.domain.usecase

import `in`.ev.subreddit.domain.model.SubRedditPost
import `in`.ev.subreddit.domain.repository.SubRedditRepository
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPostsUseCaseImpl  @Inject constructor(private val repository:
                                               SubRedditRepository
): GetRedditPostUsecase {
    @OptIn(ExperimentalPagingApi::class, FlowPreview::class)
    override suspend fun execute(limit: Int): Flow<PagingData<SubRedditPost>> {
        return repository.getSubRedditPosts(limit)
    }

}