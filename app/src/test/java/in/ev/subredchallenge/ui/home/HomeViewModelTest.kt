package `in`.ev.subredchallenge.ui.home

import `in`.ev.subreddit.domain.model.Error
import `in`.ev.subreddit.domain.model.Response
import `in`.ev.subredchallenge.CoroutinesTestRule
import `in`.ev.subreddit.domain.model.SubRedditPost
import `in`.ev.subreddit.domain.usecase.GetPostsUseCaseImpl
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.PagingData
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before

import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class HomeViewModelTest {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @MockK
    private lateinit var useCase: GetPostsUseCaseImpl

    private lateinit var viewModel: HomeViewModel

    @get:Rule
    var coroutinesTestRule = CoroutinesTestRule()

    val posts1 = SubRedditPost(
        id = "sadsa",
        author = "to_and",
        authorFullname = "sad",
        createdUtc = 10090.0,
        name = "sadsa",
        numComments = 67,
        score = 543,
        subreddit = "Android",
        subredditId = "dsdf",
        subredditType = "asgsjhd",
        thumbnail = "https://images.amcnetworks.com/amc\" +\n" +
                "                \".com/wp-content/uploads/2015/04/cast_bb_700x1000_walter-white" +
                "-lg.jpg",
        title = "Lorem ipsum",
        totalAwardsReceived = 342,
        ups = 1223,
        url = "https://images.amcnetworks.com/amc\" +\n" +
                "                \".com/wp-content/uploads/2015/04/cast_bb_700x1000_walter-white-lg.jpg")
    val postList: List<SubRedditPost> = mutableListOf<SubRedditPost>(posts1)

    val postsPagingData = PagingData.from(postList)
    val errorResponse = Error( status_message = "service error")

/*
    private val networkResultSuccess = Response.ApiCallSuccess<List<Character>>(characters)
    private val networkResultError = Response.ApiCallError((errorResponse))
*/

    private val networkLoadingT = Response.Loading<Boolean>(true)
    private val networkLoadingF = Response.Loading<Boolean>(false)

    private val flowSuccess = flowOf(postsPagingData)
    private val flowFailure = flowOf(errorResponse)
    private val flowLoadingFalse = flowOf(networkLoadingF)
    private val flowLoadingTrue = flowOf(networkLoadingT)
    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        viewModel = HomeViewModel(useCase)
    }

    @Test
    fun Given_Usecase_Get_Posts_When_Get_Posts_Then_Api_Success() =
        coroutinesTestRule.testDispatcher.runBlockingTest {
            coEvery {
                useCase.execute(any())
            } returns flowSuccess
            viewModel.getSubRedditPosts()
            flowSuccess.collect {  data ->
                assertNotNull(data)
                //data.data?.isNotEmpty()?.let { assert(it) }
            }

        }

}