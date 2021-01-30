package `in`.ev.subreddit.data.local

import `in`.ev.subreddit.data.model.local.TblSubReddit
import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface SubRedditPostDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(posts: List<TblSubReddit>)

    @Query("SELECT * FROM posts WHERE id = :postId")
    fun getPostsById(postId: String): PagingSource<Int, TblSubReddit>

    @Query("DELETE FROM posts")
    suspend fun clearAllPosts()

    @Query("DELETE FROM posts WHERE id = :id")
    suspend fun deleteById(id: String)
}