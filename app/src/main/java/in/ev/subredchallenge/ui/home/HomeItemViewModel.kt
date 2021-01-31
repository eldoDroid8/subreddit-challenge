package `in`.ev.subredchallenge.ui.home

import `in`.ev.subredchallenge.ui.base.BaseListItemViewModel
import `in`.ev.subredchallenge.ui.RecyclerviewItemSelected
import `in`.ev.subreddit.domain.model.SubRedditPost
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class HomeItemViewModel(val post: SubRedditPost?): BaseListItemViewModel {
    val id = post?.id
    val title: LiveData<String> = MutableLiveData(post?.title)
    val author: LiveData<String> = MutableLiveData(post?.author)
    val score: LiveData<String> = MutableLiveData(post?.score?.toString())
    val thumbUrl: LiveData<String> = MutableLiveData(post?.thumbnail)

    override fun equals(other: Any?) = (other is HomeItemViewModel)
            && id == other.id
}