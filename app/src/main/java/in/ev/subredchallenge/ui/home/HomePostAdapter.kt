package `in`.ev.subredchallenge.ui.home

import `in`.ev.subredchallenge.R
import `in`.ev.subredchallenge.databinding.ListItemHomeBinding
import `in`.ev.subredchallenge.utils.POST_SUB_DIFF_UTIL
import `in`.ev.subreddit.domain.model.SubRedditPost
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.load.engine.DiskCacheStrategy

class HomePostAdapter(
    private val itemSelected:
    RecyclerviewItemSelected<SubRedditPost?>
) : PagingDataAdapter<SubRedditPost, HomePostAdapter.RecyclerViewHolder>(POST_SUB_DIFF_UTIL) {

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.bind(HomeItemViewModel(getItem(position), itemSelected))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        RecyclerViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.list_item_home,
                parent,
                false
            )
        )


    class RecyclerViewHolder(private val binding: ViewDataBinding) : RecyclerView.ViewHolder(
        binding.root
    ) {

        fun bind(obj: Any?) {
            binding.setVariable(androidx.databinding.library.baseAdapters.BR.obj, obj)
        }

    }

}
typealias  RecyclerviewItemSelected<T> = (T) -> Unit


