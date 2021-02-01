package `in`.ev.subredchallenge.utils

import `in`.ev.subreddit.domain.model.SubRedditPost
import androidx.recyclerview.widget.DiffUtil

val POST_SUB_DIFF_UTIL = object : DiffUtil.ItemCallback<SubRedditPost>() {
    override fun areContentsTheSame(oldItem: SubRedditPost, newItem: SubRedditPost): Boolean =
        oldItem == newItem

    override fun areItemsTheSame(oldItem: SubRedditPost, newItem: SubRedditPost): Boolean =
        oldItem.id == newItem.id

}