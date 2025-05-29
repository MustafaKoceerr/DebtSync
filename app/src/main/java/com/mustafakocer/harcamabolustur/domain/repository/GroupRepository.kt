package com.mustafakocer.harcamabolustur.domain.repository

import androidx.paging.PagingData
import com.mustafakocer.harcamabolustur.domain.model.Group
import com.mustafakocer.harcamabolustur.domain.model.GroupMember
import com.mustafakocer.harcamabolustur.domain.model.User
import kotlinx.coroutines.flow.Flow

interface GroupRepository {

    // Paging3 - Ana ekran
    fun getUserGroups(userId: String): Flow<PagingData<Group>>

    // Group operations
    suspend fun getGroupById(groupId: String): Group?
    fun getGroupBuIdFlow(groupId: String): Flow<Group?>
    suspend fun createGroup(group: Group, currentUserId: String): Result<String>
    suspend fun updateGroup(group: Group): Result<Unit>
    suspend fun deleteGroup(groupId: String, userId: String): Result<Unit>

    // Group validation
    suspend fun isUserMemberOfGroup(groupId: String, userId: String): Boolean
    suspend fun isUserAdminOfGroup(groupId: String, userId: String): Boolean
    suspend fun generateUniqueInviteCode(): String

    // Members
    fun getGroupMembers(groupId: String): Flow<List<GroupMember>>
    suspend fun addMemberToGroup(groupId: String, userId: String, role: String): Result<Unit>
    suspend fun removeMemberToGroup(groupId: String, userId: String): Result<Unit>
    suspend fun updateMemberRole(groupId: String, userId: String, role: String): Result<Unit>
}