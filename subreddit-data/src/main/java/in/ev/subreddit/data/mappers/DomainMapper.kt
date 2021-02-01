package `in`.ev.subreddit.data.mappers

import `in`.ev.subreddit.data.model.remote.SubRedditInfo
import `in`.ev.subreddit.data.utils.Constants
import `in`.ev.subreddit.domain.model.SubRedditPost

fun toDomain(subReddit: SubRedditInfo): SubRedditPost {
    return SubRedditPost(
        subReddit.id, Constants.POSTED_BY + subReddit.author, subReddit
            .authorFullname, subReddit.createdUtc, subReddit.name, subReddit.numComments, subReddit
            .score, subReddit
            .subreddit, subReddit.subredditId, subReddit.subredditType, subReddit.thumbnail,
        subReddit.title, subReddit.totalAwardsReceived, subReddit.ups,
        subReddit.url
    )
}
