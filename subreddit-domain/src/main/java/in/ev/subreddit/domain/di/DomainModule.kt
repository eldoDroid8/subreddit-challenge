package `in`.ev.subreddit.domain.di

import `in`.ev.subreddit.domain.usecase.GetPostsUseCaseImpl
import `in`.ev.subreddit.domain.usecase.GetRedditPostUsecase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
abstract class UseCaseModule<T: Any> {
    @Binds
    @Singleton
    abstract fun bindGetRedditPostUseCase(useCaseImpl: GetPostsUseCaseImpl<T>):
            GetRedditPostUsecase<T>

}