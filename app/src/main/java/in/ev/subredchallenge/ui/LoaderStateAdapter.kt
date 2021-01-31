package `in`.ev.subredchallenge.ui

import `in`.ev.subredchallenge.R
import `in`.ev.subredchallenge.databinding.RecycleLoadStateItemBinding
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView

class LoaderStateAdapter(private val retry: () -> Unit) :
    LoadStateAdapter<LoaderStateAdapter.LoaderViewHolder>() {

    override fun onBindViewHolder(holder: LoaderViewHolder, loadState: LoadState) {
        holder.bindTo(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): LoaderViewHolder {
        return LoaderViewHolder(parent) { retry() }
    }

    class LoaderViewHolder(
        parent: ViewGroup,
        private val retryCallback: () -> Unit
    ) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.recycle_load_state_item, parent, false)
    ) {
        private val binding = RecycleLoadStateItemBinding.bind(itemView)
        private val progressBar = binding.progressBar
        private val errorMsg = binding.errorMsg
        private val retry = binding.retryButton
            .also {
                it.setOnClickListener { retryCallback() }
            }

        fun bindTo(loadState: LoadState) {
            progressBar.isVisible = loadState is LoadState.Loading
            retry.isVisible = loadState is LoadState.Error
            errorMsg.isVisible = !(loadState as? LoadState.Error)?.error?.message.isNullOrBlank()
            errorMsg.text = (loadState as? LoadState.Error)?.error?.message
        }
    }

}