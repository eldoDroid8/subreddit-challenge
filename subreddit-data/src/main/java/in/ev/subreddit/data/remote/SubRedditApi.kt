package `in`.ev.subreddit.data.remote

import `in`.ev.subreddit.data.model.remote.SubRedditEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SubRedditApi {
    @GET("Android/hot.json?")
    suspend fun getSubReddits(@Query("limit") limit: String, @Query("after") after: String,
                              @Query("before") before: String):
            Response<SubRedditEntity>

}