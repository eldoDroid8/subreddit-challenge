package `in`.ev.subredchallenge.utils

import `in`.ev.subredchallenge.ui.home.HomeItemViewModel
import `in`.ev.subreddit.data.model.remote.SubRedditInfo
import `in`.ev.subreddit.domain.model.SubRedditPost
import androidx.recyclerview.widget.DiffUtil

class PostsDiffCallBack : DiffUtil.ItemCallback<SubRedditPost>() {
    override fun areItemsTheSame(oldItem: SubRedditPost, newItem: SubRedditPost): Boolean {
        return oldItem.id == newItem.id;
    }

    override fun areContentsTheSame(oldItem: SubRedditPost, newItem: SubRedditPost): Boolean {
        return oldItem == newItem
    }
}


val POST_DIFF_UTIL = object : DiffUtil.ItemCallback<HomeItemViewModel>() {
    override fun areContentsTheSame(oldItem: HomeItemViewModel, newItem: HomeItemViewModel): Boolean =
        oldItem == newItem

    override fun areItemsTheSame(oldItem: HomeItemViewModel, newItem: HomeItemViewModel): Boolean =
        oldItem.id == newItem.id

}

val POST_SUB_DIFF_UTIL = object : DiffUtil.ItemCallback<SubRedditPost>() {
    override fun areContentsTheSame(oldItem: SubRedditPost, newItem: SubRedditPost): Boolean =
        oldItem == newItem

    override fun areItemsTheSame(oldItem: SubRedditPost, newItem: SubRedditPost): Boolean =
        oldItem.id == newItem.id

}