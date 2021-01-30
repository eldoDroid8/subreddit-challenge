package `in`.ev.subreddit.domain.usecase

interface GetRedditPostUsecase<out T> {
    suspend fun execute(postId: String, limit: Int): T
}