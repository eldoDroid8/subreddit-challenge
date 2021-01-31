package `in`.ev.subreddit.data.mappers

import `in`.ev.subreddit.data.model.local.TblSubReddit
import `in`.ev.subreddit.data.model.remote.SubRedditInfo
import `in`.ev.subreddit.domain.model.SubRedditPost

fun TblSubReddit.toDomain(tblSubReddit: TblSubReddit): SubRedditPost {
    return SubRedditPost(tblSubReddit.id,tblSubReddit.author,tblSubReddit.authorFullname, tblSubReddit
        .createdUtc, tblSubReddit.name, tblSubReddit.numComments, tblSubReddit.score, tblSubReddit
        .subreddit, tblSubReddit.subredditId,  tblSubReddit.subredditType, tblSubReddit
        .thumbnail, tblSubReddit.title, tblSubReddit.totalAwardsReceived, tblSubReddit.ups,
        tblSubReddit.url)
}

fun SubRedditInfo.toDomain(subReddit: SubRedditInfo): SubRedditPost {
    return SubRedditPost(subReddit.id,subReddit.author,subReddit.authorFullname, subReddit
        .createdUtc, subReddit.name, subReddit.numComments, subReddit.score, subReddit
        .subreddit, subReddit.subredditId, subReddit.subredditType, subReddit.thumbnail,
        subReddit.title, subReddit.totalAwardsReceived, subReddit.ups,
        subReddit.url)
}
