package `in`.ev.subreddit.data.di.module

import `in`.ev.data.model.ErrorEntity
import `in`.ev.subreddit.domain.repository.SubRedditRepository
import `in`.ev.subreddit.data.datasource.remote.SubRedditRemoteDataSource
import `in`.ev.subreddit.data.remote.SubRedditApi
import `in`.ev.subreddit.data.repository.remote.RemoteRepoImpl
import android.content.Context
import androidx.room.Room
import com.squareup.moshi.JsonAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
object DataSourceModule {
    @Provides
    @Singleton
    fun provideDataSource(
        api: SubRedditApi, retrofit: Retrofit, moshiAdapter:
        JsonAdapter<ErrorEntity>
    ): SubRedditRemoteDataSource {
        return SubRedditRemoteDataSource(api, retrofit, moshiAdapter)
    }

}

@Module
@InstallIn(ApplicationComponent::class)
object RepoModule {
    @Provides
    @Singleton
    fun provideRepoImpl(
        dataSource: SubRedditRemoteDataSource
    ): SubRedditRepository {
        return RemoteRepoImpl(dataSource)
    }

}
