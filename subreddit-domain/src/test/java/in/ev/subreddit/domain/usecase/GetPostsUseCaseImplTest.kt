package `in`.ev.subreddit.domain.usecase

import `in`.ev.domain.repository.SubRedditRepository
import `in`.ev.subreddit.domain.CoroutinesTestRule
import `in`.ev.subreddit.domain.model.SubRedditPost
import androidx.paging.PagingData
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class GetPostsUseCaseImplTest {
    @get:Rule
    var coroutinesTestRule = CoroutinesTestRule()

    @MockK
    private lateinit var repository: SubRedditRepository


    private lateinit var useCaseImpl: GetPostsUseCaseImpl

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        useCaseImpl = GetPostsUseCaseImpl(repository)
    }

    @Test
    fun verifyRepository() = coroutinesTestRule.testDispatcher.runBlockingTest {
        coEvery {
            useCaseImpl.execute(any())
        }
       coVerify(exactly = 0) { repository.getSubRedditPosts(any()) }
    }
 }
