package `in`.ev.subredchallenge.ui.home

import `in`.ev.subredchallenge.BR
import `in`.ev.subredchallenge.R
import `in`.ev.subredchallenge.databinding.FragmentHomeBinding
import `in`.ev.subredchallenge.ui.LoaderStateAdapter
import `in`.ev.subredchallenge.ui.RedditPostAdapter
import `in`.ev.subredchallenge.utils.POST_DIFF_UTIL
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var adapter: HomePostAdapter

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
        /*setUpSearch()
        observeData()*/
    }

    private fun initRecycerView() {
        val items: MutableList<HomeItemViewModel> = mutableListOf<HomeItemViewModel>()
        // adapter = RedditPostAdapter(R.layout.list_item_home,items, POST_DIFF_UTIL)
        adapter = HomePostAdapter()
        homeLayoutbinding.rvPosts.layoutManager = GridLayoutManager(context, 1)
        homeLayoutbinding.rvPosts.adapter= adapter.withLoadStateFooter(LoaderStateAdapter { adapter.retry() })
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

}