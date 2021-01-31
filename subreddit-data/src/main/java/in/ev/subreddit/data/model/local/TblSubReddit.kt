package `in`.ev.subreddit.data.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(tableName = "posts")
class TblSubReddit (
    @PrimaryKey
    val id: String,
    val author: String,
    val authorFullname: String,
    val createdUtc: Double,
    val name: String,
    val numComments: Int,
    val score: Int,
    val subreddit: String,
    val subredditId: String,
    val subredditType: String,
    val thumbnail: String,
    val title: String,
    val totalAwardsReceived: Int?,
    val ups: Int?,
    val url: String?
)