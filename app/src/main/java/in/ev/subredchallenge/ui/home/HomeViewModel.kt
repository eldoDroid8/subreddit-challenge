package `in`.ev.subredchallenge.ui.home

import `in`.ev.subreddit.domain.model.Error
import `in`.ev.subredchallenge.ui.ViewState
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
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onCompletion

class HomeViewModel @ViewModelInject constructor(
    private val getRedditPostUsecase: GetRedditPostUsecase
) : ViewModel() {
    private val mutablestateNav: MutableLiveData<ViewState<PagingData<SubRedditPost>>> = MutableLiveData()
    val stateNav: LiveData<ViewState<PagingData<SubRedditPost>>> = mutablestateNav

    @OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
    suspend fun getSubRedditPosts(): Flow<PagingData<SubRedditPost>> {
        return getRedditPostUsecase.execute(Constants.PAGE_LIMIT).cachedIn(viewModelScope)
            .handleErrors()
    }

    //ToDO Move this to BaseViewModel later
    private fun <T> Flow<T>.handleErrors(): Flow<T> =
        catch { e ->
            mutablestateNav.value = ViewState.Failure(Error(status_message = e.message))
        }
}
