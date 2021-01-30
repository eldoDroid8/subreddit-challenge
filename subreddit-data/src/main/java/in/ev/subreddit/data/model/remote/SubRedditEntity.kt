package `in`.ev.subreddit.data.model.remote
import com.squareup.moshi.JsonClass

import com.squareup.moshi.Json


@JsonClass(generateAdapter = true)
data class SubRedditEntity(
    @Json(name = "data")
    val `data`: Data,
)

@JsonClass(generateAdapter = true)
data class Data(
    @Json(name = "after")
    val after: String,
    @Json(name = "before")
    val before: String,
    @Json(name = "children")
    val children: List<Children>,
)

@JsonClass(generateAdapter = true)
data class Children(
    @Json(name = "data")
    val `data`: SubRedditInfo,
)

@JsonClass(generateAdapter = true)
data class SubRedditInfo(
    @Json(name = "author")
    val author: String,
    @Json(name = "author_fullname")
    val authorFullname: String,
    @Json(name = "created_utc")
    val createdUtc: Double,
    @Json(name = "id")
    val id: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "num_comments")
    val numComments: Int,
    @Json(name = "score")
    val score: Int,
    @Json(name = "subreddit")
    val subreddit: String,
    @Json(name = "subreddit_id")
    val subredditId: String,
    @Json(name = "subreddit_name_prefixed")
    val subredditNamePrefixed: String,
    @Json(name = "subreddit_type")
    val subredditType: String,
    @Json(name = "thumbnail")
    val thumbnail: String,
    @Json(name = "thumbnail_height")
    val thumbnailHeight: Int,
    @Json(name = "thumbnail_width")
    val thumbnailWidth: Int,
    @Json(name = "title")
    val title: String,
    @Json(name = "total_awards_received")
    val totalAwardsReceived: Int,
    @Json(name = "ups")
    val ups: Int,
    @Json(name = "url")
    val url: String,
)

