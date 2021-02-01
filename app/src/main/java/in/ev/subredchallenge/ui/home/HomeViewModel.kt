package `in`.ev.subredchallenge.ui.home

import `in`.ev.subredchallenge.utils.Constants
import `in`.ev.subreddit.domain.model.SubRedditPost
import `in`.ev.subreddit.domain.usecase.GetRedditPostUsecase
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow

class HomeViewModel  @ViewModelInject constructor(
    private val getRedditPostUsecase: GetRedditPostUsecase
) : ViewModel() {

    @OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
    suspend fun getSubRedditPosts(): Flow<PagingData<SubRedditPost>> {
        return  getRedditPostUsecase.execute("",Constants.PAGE_LIMIT).cachedIn(viewModelScope)
    }
}
