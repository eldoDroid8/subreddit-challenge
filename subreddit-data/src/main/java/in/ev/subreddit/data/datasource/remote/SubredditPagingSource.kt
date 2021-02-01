
package `in`.ev.subreddit.data.datasource.remote

import `in`.ev.data.model.EntityResultWrapper
import `in`.ev.subreddit.data.model.remote.Children
import androidx.paging.PagingSource
import androidx.paging.PagingSource.LoadResult.Page
import androidx.paging.PagingState

class SubredditPagingSource(
    private val subRedditRemoteDataSource: SubRedditRemoteDataSource
) : PagingSource<String, Children>() {
    override suspend fun load(params: LoadParams<String>): LoadResult<String, Children> {
        val response = subRedditRemoteDataSource.getSubRedditPosts(
            params.loadSize.toString(),
            after = params.key ?: "",
            before = params.key ?: ""
        )

        return when (response) {
                is EntityResultWrapper.Success -> {
                    val data = response.data.data.children
                        Page(
                            data = data, prevKey = response.data.data.before,
                            nextKey = response.data.data.after
                        )
                    }
                is EntityResultWrapper.Error -> {
                    LoadResult.Error(throwable = Throwable(response.error.status_message))
                }
            }
        }

    override fun getRefreshKey(state: PagingState<String, Children>): String {
        return ""
    }

}
