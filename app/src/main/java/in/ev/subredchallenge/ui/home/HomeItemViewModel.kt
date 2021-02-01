package `in`.ev.subredchallenge.ui.home

import `in`.ev.subreddit.domain.model.SubRedditPost
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class HomeItemViewModel(
    private val post: SubRedditPost?, private val itemSelected:
    RecyclerviewItemSelected<SubRedditPost>
) {
    val id = post?.id
    val title: LiveData<String> = MutableLiveData(post?.title)
    val author: LiveData<String> = MutableLiveData(post?.author)
    val score: LiveData<String> = MutableLiveData(post?.score?.toString())
    val thumbUrl: LiveData<String> = MutableLiveData(post?.thumbnail)

    fun onPostSelected() {
        if (post != null) {
            itemSelected.invoke(post)
        }
    }

}