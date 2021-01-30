package `in`.ev.subreddit.data.mappers
import `in`.ev.data.model.ErrorEntity
import `in`.ev.subreddit.data.model.local.TblSubReddit
import `in`.ev.subreddit.data.model.remote.SubRedditInfo

/*fun toDomain(errorEntity: ErrorEntity): Error {
    return Error(errorEntity.status_code, errorEntity.status_message)
}*/

fun SubRedditInfo.toRoomEntity(redditInfo: SubRedditInfo): TblSubReddit {
    return TblSubReddit(redditInfo.id,redditInfo.author,redditInfo.authorFullname, redditInfo
        .createdUtc, redditInfo.name, redditInfo.numComments, redditInfo.score, redditInfo
        .subreddit, redditInfo.subredditId, redditInfo.subredditNamePrefixed, redditInfo
        .subredditType, redditInfo.thumbnail, redditInfo.thumbnailHeight, redditInfo
        .thumbnailWidth, redditInfo.title, redditInfo.totalAwardsReceived, redditInfo.ups,
        redditInfo.url)
}

