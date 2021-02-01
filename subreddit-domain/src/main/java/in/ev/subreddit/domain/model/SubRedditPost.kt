package `in`.ev.subreddit.domain.model

data class SubRedditPost (
    val id: String,
    val author: String,
    val authorFullname: String?,
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
    val url: String?,
)