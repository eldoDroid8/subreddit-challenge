package `in`.ev.subreddit.data.di.module

import `in`.ev.data.model.ErrorEntity
import `in`.ev.domain.repository.SubRedditPostsRepository
import `in`.ev.subreddit.data.datasource.remote.SubRedditRemoteDataSource
import `in`.ev.subreddit.data.local.SubRedditDatabase
import `in`.ev.subreddit.data.model.local.TblSubReddit
import `in`.ev.subreddit.data.remote.SubRedditApi
import `in`.ev.subreddit.data.repository.local.RedditDbRepositoryImpl
import android.content.Context
import androidx.paging.PagingData
import androidx.room.Room
import com.squareup.moshi.JsonAdapter
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideRoomDb(@ApplicationContext context: Context): SubRedditDatabase {
        val dbBuilder = Room.databaseBuilder(context, SubRedditDatabase::class.java, "subreddit.db")
        return dbBuilder
        .fallbackToDestructiveMigration()
        .build()
    }

}


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
        db:SubRedditDatabase, dataSource: SubRedditRemoteDataSource
    ): SubRedditPostsRepository<Any> {
        return RedditDbRepositoryImpl(db, dataSource)
    }
}
