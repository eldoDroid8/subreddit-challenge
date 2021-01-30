package `in`.ev.subreddit.domain.usecase

import `in`.ev.domain.model.Response
import `in`.ev.domain.repository.SubRedditPostsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPostsUseCaseImpl<T: Any>  @Inject constructor(private val repository:
                                                       @JvmSuppressWildcards
                                                       SubRedditPostsRepository<T>): GetRedditPostUsecase<T> {
    override suspend fun execute(postId: String, limit: Int): T {
        return repository.getSubRedditPosts(postId, limit)
    }

}