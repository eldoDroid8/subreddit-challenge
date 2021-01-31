
package `in`.ev.subredchallenge.ui

import `in`.ev.subredchallenge.BR
import `in`.ev.subredchallenge.ui.base.BaseListItemViewModel
import `in`.ev.subredchallenge.utils.PostsDiffCallBack
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

class RedditPostAdapter<T: BaseListItemViewModel>(val layoutId: Int, val listItems: List<T>,
                                                     diffCallBack: DiffUtil.ItemCallback<T>)
    : PagingDataAdapter<T, RedditPostAdapter.RecyclerViewHolder>(diffCallBack) {

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        val bindingStatus = holder.binding.setVariable(BR.obj, listItems[position])
        if (!bindingStatus) {
            throw IllegalStateException("Binding ${holder.binding} variable name should be 'viewModel'")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        RecyclerViewHolder(
            DataBindingUtil.inflate(LayoutInflater.from(parent.context), layoutId, parent, false))


    class RecyclerViewHolder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root)

}

typealias  RecyclerviewItemSelected<T> = (T) -> Unit
