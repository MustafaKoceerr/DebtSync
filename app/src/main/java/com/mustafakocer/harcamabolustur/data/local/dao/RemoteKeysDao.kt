package com.mustafakocer.harcamabolustur.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mustafakocer.harcamabolustur.data.local.entities.RemoteKeysEntity

// ============ REMOTE KEYS DAO ============
@Dao
interface RemoteKeysDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRemoteKeys(keys: RemoteKeysEntity)

    @Query("SELECT * FROM remote_keys WHERE entityId = :entityId AND entityType = :entityType")
    suspend fun getRemoteKeys(entityId: String, entityType: String): RemoteKeysEntity?

    @Query("DELETE FROM remote_keys WHERE entityType = :entityType")
    suspend fun clearRemoteKeys(entityType: String)
}