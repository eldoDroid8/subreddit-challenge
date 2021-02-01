package `in`.ev.subredchallenge.ui.home

import `in`.ev.subredchallenge.BR
import `in`.ev.subredchallenge.databinding.FragmentHomeBinding
import `in`.ev.subredchallenge.ui.ViewState
import `in`.ev.subredchallenge.ui.widgets.LoaderStateAdapter
import `in`.ev.subreddit.domain.model.SubRedditPost
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var adapter: HomePostAdapter
    val itemSelected: RecyclerviewItemSelected<SubRedditPost?>
        get() = this::postSelected

    private lateinit var homeLayoutbinding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeLayoutbinding = FragmentHomeBinding.inflate(inflater, container, false)
        homeLayoutbinding.lifecycleOwner = viewLifecycleOwner
        return homeLayoutbinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initDataBinding()
        initRecycerView()
        fetchPosts()
        observeData()
        /*setUpSearch()
        observeData()*/
    }

    private fun initRecycerView() {
        adapter = HomePostAdapter(itemSelected)
        homeLayoutbinding.rvPosts.layoutManager = GridLayoutManager(context, 1)
        homeLayoutbinding.rvPosts.adapter = adapter.withLoadStateFooter(
            LoaderStateAdapter { adapter.retry() })

    }

    private fun fetchPosts() {
        lifecycleScope.launch {
            homeViewModel.getSubRedditPosts().distinctUntilChanged().collectLatest {
                adapter.submitData(it)
            }
        }
    }

    private fun initDataBinding() {
        homeLayoutbinding.setVariable(BR.viewModel, homeViewModel)
        homeLayoutbinding.executePendingBindings()
    }

    private fun postSelected(post: SubRedditPost?) {
        post?.url?.let { url ->
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }
    }

    private fun observeData() {
        homeViewModel.stateNav.observe(viewLifecycleOwner, {
            when (it) {
                is ViewState.Failure -> {
                    it.throwable.status_message?.let { msg ->
                        showMsg(msg)
                    }
                }
                else -> {
                    showMsg("Something went wrong")
                }
            }
        })

    }

    private fun showMsg(msg: String) {
        val snackbar = Snackbar.make(homeLayoutbinding.containerMain, msg, Snackbar.LENGTH_LONG)
        snackbar.show()
    }

}