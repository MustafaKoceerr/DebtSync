package com.mustafakocer.harcamabolustur.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.mustafakocer.harcamabolustur.data.local.entities.UserEntity
import com.mustafakocer.harcamabolustur.data.local.entities.UserRelationshipType
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    // Insert/Update
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsers(users: List<UserEntity>)

    @Update
    suspend fun updateUser(user: UserEntity)

    // Queries
    @Query("SELECT * FROM users WHERE id = :userId")
    suspend fun getUserById(userId: String): UserEntity?

    @Query("SELECT * FROM users WHERE id = :userId")
    fun getUserByIdFlow(userId: String): Flow<UserEntity?>

    @Query("SELECT * FROM users WHERE username = :username")
    suspend fun getUserByUsername(username: String): UserEntity?

    // İLİŞKİ BAZLI SORGULAR
    @Query("SELECT * FROM users WHERE relationshipType = :type AND isActive = 1")
    fun getUsersByRelationship(type: UserRelationshipType): Flow<List<UserEntity>>

    @Query("SELECT * FROM users WHERE relationshipType IN ('GROUP_MEMBER') AND isActive = 1")
    fun getGroupMembers(): Flow<List<UserEntity>>

    @Query("SELECT * FROM users WHERE relationshipType = 'SELF'")
    suspend fun getCurrentUser(): UserEntity?

    @Query("SELECT * FROM users WHERE relationshipType = 'SELF'")
    fun getCurrentUserFlow(): Flow<UserEntity?>

    // GRUP İÇİN KULLANICILAR (Expense eklerken göstereceğiz)
    @Query(
        """
        SELECT DISTINCT u.* FROM users u
        INNER JOIN group_members gm ON u.id = gm.userId
        WHERE gm.groupId = :groupId AND gm.isActive = 1 AND u.isActive = 1
    """
    )
    fun getUsersInGroup(groupId: String): Flow<List<UserEntity>>

    // Sync operations
    @Query("SELECT * FROM users WHERE needsSync = 1")
    suspend fun getUsersNeedingSync(): List<UserEntity>

    @Query("UPDATE users SET needsSync = 0, lastSyncedAt = :syncTime WHERE id = :userId")
    suspend fun markAsSynced(userId: String, syncTime: Long)

    // Delete/Cleanup
    @Query("UPDATE users SET isActive = 0 WHERE id = :userId")
    suspend fun deactivateUser(userId: String)

    @Query("DELETE FROM users WHERE relationshipType != 'SELF' AND addedAt < :cutoffTime")
    suspend fun cleanupOldUsers(cutoffTime: Long) // Eski ilişkisiz kullanıcıları temizle

    @Query("DELETE FROM users")
    suspend fun clearAllUsers()
}