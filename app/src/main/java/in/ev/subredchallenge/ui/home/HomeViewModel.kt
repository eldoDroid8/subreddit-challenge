package `in`.ev.subredchallenge.ui.home

import `in`.ev.subredchallenge.ui.RecyclerviewItemSelected
import `in`.ev.subredchallenge.ui.ViewState
import `in`.ev.subreddit.data.model.remote.SubRedditInfo
import `in`.ev.subreddit.domain.model.SubRedditPost
import `in`.ev.subreddit.domain.usecase.GetRedditPostUsecase
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeViewModel  @ViewModelInject constructor(
    private val getRedditPostUsecase: GetRedditPostUsecase
) : ViewModel() {
    //private val homeNavEvents = SingleLiveEvent<HomeNavigation>()
    //val stateHomeEvents: LiveData<HomeNavigation> = homeNavEvents
    private val postList = MutableLiveData<List<SubRedditPost>>()
    val observableList: LiveData<List<SubRedditPost>> = this.postList
    val showShimmerAnimation = MutableLiveData(true)
    val itemSelected: RecyclerviewItemSelected<SubRedditPost>
        get() = this::postSelected
    private val postLiveData: MutableLiveData<ViewState<List<SubRedditPost>>> = MutableLiveData()
    val stateNav: LiveData<ViewState<List<SubRedditPost>>> = postLiveData

    @OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
    suspend fun getSubRedditPosts(): Flow<PagingData<SubRedditPost>> {
        return  getRedditPostUsecase.execute("",10).cachedIn(viewModelScope)
    }

    /*@OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
    fun getSubRedditPosts(): Flow<PagingData<SubRedditInfo>> {
        return repository.getSubRedditPosts("",10).cachedIn(viewModelScope)
    }
*/
    private fun postSelected(post: SubRedditPost) {
        ///homeNavEvents.value = HomeNavigation.NavigateToDetail(character)
    }

}
