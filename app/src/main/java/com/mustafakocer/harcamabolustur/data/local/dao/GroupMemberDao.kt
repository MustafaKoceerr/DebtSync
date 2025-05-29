package com.mustafakocer.harcamabolustur.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.mustafakocer.harcamabolustur.data.local.entities.GroupMemberEntity
import com.mustafakocer.harcamabolustur.data.local.entities.GroupRole
import kotlinx.coroutines.flow.Flow

@Dao
interface GroupMemberDao {

    // ============ INSERT/UPDATE ============
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGroupMember(member: GroupMemberEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGroupMembers(members: List<GroupMemberEntity>)

    @Update
    suspend fun updateGroupMember(member: GroupMemberEntity)

    // ============ QUERIES ============
    @Query("SELECT * FROM group_members WHERE groupId = :groupId AND isActive = 1 ORDER BY joinedAt ASC")
    fun getGroupMembers(groupId: String): Flow<List<GroupMemberEntity>>

    @Query("SELECT * FROM group_members WHERE groupId = :groupId AND userId = :userId AND isActive = 1")
    suspend fun getGroupMember(groupId: String, userId: String): GroupMemberEntity?

    @Query("SELECT * FROM group_members WHERE groupId = :groupId AND role = :role AND isActive = 1")
    fun getGroupMembersByRole(groupId: String, role: GroupRole): Flow<List<GroupMemberEntity>>

    // Admin kontrolü
    @Query("SELECT * FROM group_members WHERE groupId = :groupId AND role = 'ADMIN' AND isActive = 1")
    fun getGroupAdmins(groupId: String): Flow<List<GroupMemberEntity>>

    // Grup üye sayısı
    @Query("SELECT COUNT(*) FROM group_members WHERE groupId = :groupId AND isActive = 1")
    fun getGroupMemberCount(groupId: String): Flow<Int>

    // Kullanıcının grup rolü
    @Query("SELECT role FROM group_members WHERE groupId = :groupId AND userId = :userId AND isActive = 1")
    suspend fun getUserRoleInGroup(groupId: String, userId: String): GroupRole?

    // ============ ADMIN OPERATIONS ============
    // Kullanıcıyı admin yap
    @Query("UPDATE group_members SET role = 'ADMIN', needsSync = 1 WHERE groupId = :groupId AND userId = :userId")
    suspend fun promoteToAdmin(groupId: String, userId: String)

    // Admin'i member yap
    @Query("UPDATE group_members SET role = 'MEMBER', needsSync = 1 WHERE groupId = :groupId AND userId = :userId")
    suspend fun demoteToMember(groupId: String, userId: String)

    // ============ NOTIFICATION SETTINGS ============
    @Query("UPDATE group_members SET notificationsEnabled = :enabled, needsSync = 1 WHERE groupId = :groupId AND userId = :userId")
    suspend fun updateNotificationSetting(groupId: String, userId: String, enabled: Boolean)

    @Query("SELECT notificationsEnabled FROM group_members WHERE groupId = :groupId AND userId = :userId AND isActive = 1")
    suspend fun getNotificationSetting(groupId: String, userId: String): Boolean?

    // ============ SYNC OPERATIONS ============
    @Query("SELECT * FROM group_members WHERE needsSync = 1")
    suspend fun getMembersNeedingSync(): List<GroupMemberEntity>

    @Query("UPDATE group_members SET needsSync = 0, lastSyncedAt = :syncTime WHERE id = :memberId")
    suspend fun markMemberAsSynced(memberId: String, syncTime: Long)

    // ============ DELETE/LEAVE ============
    // Gruptan ayrıl/çıkar
    @Query("UPDATE group_members SET isActive = 0, needsSync = 1 WHERE groupId = :groupId AND userId = :userId")
    suspend fun leaveGroup(groupId: String, userId: String)

    // Admin tarafından çıkarma
    @Query("UPDATE group_members SET isActive = 0, needsSync = 1 WHERE id = :memberId")
    suspend fun removeMember(memberId: String)

    @Query("DELETE FROM group_members WHERE groupId = :groupId")
    suspend fun deleteGroupMembers(groupId: String)

    @Query("DELETE FROM group_members")
    suspend fun clearAllGroupMembers()
}