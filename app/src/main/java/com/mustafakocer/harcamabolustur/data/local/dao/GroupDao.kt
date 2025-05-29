package com.mustafakocer.harcamabolustur.data.local.dao

import com.mustafakocer.harcamabolustur.data.local.entities.GroupEntity
import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface GroupDao {

    // ============ PAGING3 ============
    // Ana ekran için - kullanıcının üye olduğu gruplar
    @Query("""
        SELECT DISTINCT group_table.* FROM group_table
        INNER JOIN group_members ON group_table.id = group_members.groupId
        WHERE group_members.userId = :userId 
        AND group_members.isActive = 1 
        AND group_table.isActive = 1
        ORDER BY group_table.createdAt DESC
    """)
    fun getUserGroupsPagingSource(userId: String): PagingSource<Int, GroupEntity>

    // ============ INSERT/UPDATE ============
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGroup(group: GroupEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGroups(groups: List<GroupEntity>)

    @Update
    suspend fun updateGroup(group: GroupEntity)

    // ============ QUERIES ============
    @Query("SELECT * FROM group_table WHERE id = :groupId AND isActive = 1")
    suspend fun getGroupById(groupId: String): GroupEntity?

    @Query("SELECT * FROM group_table WHERE id = :groupId AND isActive = 1")
    fun getGroupByIdFlow(groupId: String): Flow<GroupEntity?>

    @Query("SELECT * FROM group_table WHERE inviteCode = :inviteCode AND isActive = 1")
    suspend fun getGroupByInviteCode(inviteCode: String): GroupEntity?

    // Kullanıcının admin olduğu gruplar
    @Query("""
        SELECT group_table.* FROM group_table
        INNER JOIN group_members ON group_table.id = group_members.groupId
        WHERE group_members.userId = :userId 
        AND group_members.role = 'ADMIN' 
        AND group_members.isActive = 1 
        AND group_table.isActive = 1
        ORDER BY group_table.createdAt DESC
    """)
    fun getUserAdminGroups(userId: String): Flow<List<GroupEntity>>

    // Grup sayısı (dashboard için)
    @Query("""
        SELECT COUNT(*) FROM group_table
        INNER JOIN group_members ON group_table.id = group_members.groupId
        WHERE group_members.userId = :userId 
        AND group_members.isActive = 1 
        AND group_table.isActive = 1
    """)
    fun getUserGroupCount(userId: String): Flow<Int>

    // ============ GROUP VALIDATION ============
    // Invite code unique mi kontrol et
    @Query("SELECT COUNT(*) FROM group_table WHERE inviteCode = :inviteCode AND isActive = 1")
    suspend fun isInviteCodeExists(inviteCode: String): Int

    // Kullanıcı bu grubun üyesi mi?
    @Query("""
        SELECT COUNT(*) FROM group_members
        WHERE groupId = :groupId AND userId = :userId AND isActive = 1
    """)
    suspend fun isUserMemberOfGroup(groupId: String, userId: String): Int

    // Kullanıcı bu grubun admini mi?
    @Query("""
        SELECT COUNT(*) FROM group_members
        WHERE groupId = :groupId AND userId = :userId 
        AND role = 'ADMIN' AND isActive = 1
    """)
    suspend fun isUserAdminOfGroup(groupId: String, userId: String): Int

    // ============ SYNC OPERATIONS ============
    @Query("SELECT * FROM group_table WHERE needsSync = 1")
    suspend fun getGroupsNeedingSync(): List<GroupEntity>

    @Query("UPDATE group_table SET needsSync = 0, lastSyncedAt = :syncTime WHERE id = :groupId")
    suspend fun markGroupAsSynced(groupId: String, syncTime: Long)

    // ============ DELETE/CLEANUP ============
    @Query("UPDATE group_table SET isActive = 0 WHERE id = :groupId")
    suspend fun deactivateGroup(groupId: String)

    @Query("DELETE FROM group_table WHERE id = :groupId")
    suspend fun deleteGroup(groupId: String)

    // Paging3 refresh için - kullanıcının gruplarını temizle
    @Query("""
        DELETE FROM group_table WHERE id IN (
            SELECT DISTINCT group_table.id FROM group_table
            INNER JOIN group_members ON group_table.id = group_members.groupId
            WHERE group_members.userId = :userId
        )
    """)
    suspend fun clearUserGroups(userId: String)

    @Query("DELETE FROM group_table")
    suspend fun clearAllGroups()
}