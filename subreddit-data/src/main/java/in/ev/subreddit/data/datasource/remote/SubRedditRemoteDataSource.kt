package `in`.ev.subreddit.data.datasource.remote
import `in`.ev.data.model.EntityResultWrapper
import `in`.ev.data.model.ErrorEntity
import `in`.ev.subreddit.data.model.remote.SubRedditEntity
import `in`.ev.subreddit.data.remote.SubRedditApi
import com.squareup.moshi.JsonAdapter
import retrofit2.Retrofit
import javax.inject.Inject

class SubRedditRemoteDataSource @Inject constructor(
    private val subRedditApi: SubRedditApi,
    retrofitClient: Retrofit, moshiAdapter: JsonAdapter<ErrorEntity>
) : BaseDataSource(
    retrofitClient,
    moshiAdapter
) {
    suspend fun getSubRedditPosts(limit: String,after: String = "", before: String = ""):
            EntityResultWrapper<SubRedditEntity> {
        return getResponse(
            request = { subRedditApi.getSubReddits(limit, after, before)}
        )
    }

}