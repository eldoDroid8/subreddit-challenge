package com.vikas.paging3.repository.local

import `in`.ev.subreddit.data.model.local.TblSubRedditRemoteKey
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface SubRedditKeyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(keys: TblSubRedditRemoteKey)

    @Query("SELECT * FROM pagination_keys WHERE id = :id")
    suspend fun paginationKey(id: String): TblSubRedditRemoteKey?

    @Query("DELETE FROM pagination_keys")
    suspend fun clearPaginationKeys()

    @Query("DELETE FROM pagination_keys WHERE id = :id")
    suspend fun deleteById(id: String)

}

