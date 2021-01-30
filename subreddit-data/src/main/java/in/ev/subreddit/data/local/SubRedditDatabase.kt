package `in`.ev.subreddit.data.local

import `in`.ev.subreddit.data.model.local.TblSubReddit
import `in`.ev.subreddit.data.model.local.TblSubRedditRemoteKey
import androidx.room.Database
import androidx.room.RoomDatabase
import com.vikas.paging3.repository.local.SubRedditKeyDao

@Database(version = 1, entities = [TblSubReddit::class, TblSubRedditRemoteKey::class], exportSchema = false)
abstract class SubRedditDatabase: RoomDatabase() {
    abstract fun posts(): SubRedditPostDao
    abstract fun remoteKeys(): SubRedditKeyDao
}