package `in`.ev.subreddit.domain.usecase

import `in`.ev.domain.model.Response
import `in`.ev.domain.repository.SubRedditPostsRepository
import `in`.ev.subreddit.domain.model.SubRedditPost
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPostsUseCaseImpl  @Inject constructor(private val repository:
                                                   SubRedditPostsRepository): GetRedditPostUsecase {
    @OptIn(ExperimentalPagingApi::class, FlowPreview::class)
    override suspend fun execute(postId: String, limit: Int): Flow<PagingData<SubRedditPost>> {
        return repository.getSubRedditPosts(postId, limit)
    }

}