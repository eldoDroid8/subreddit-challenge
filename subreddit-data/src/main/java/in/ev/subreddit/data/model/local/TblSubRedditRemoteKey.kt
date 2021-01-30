package `in`.ev.subreddit.data.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pagination_keys")
data class TblSubRedditRemoteKey(
    @PrimaryKey
    val id: String,
    val after: String?
)
