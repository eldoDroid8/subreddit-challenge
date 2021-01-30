package `in`.ev.domain.repository

import `in`.ev.domain.model.Response
import kotlinx.coroutines.flow.Flow

interface SubRedditPostsRepository<out T> {
    fun getSubRedditPosts(postId: String, pageSize: Int): T
}