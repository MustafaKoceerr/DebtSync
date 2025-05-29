package com.mustafakocer.harcamabolustur.data.local.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.mustafakocer.harcamabolustur.data.local.dao.GroupDao
import com.mustafakocer.harcamabolustur.data.local.dao.GroupMemberDao
import com.mustafakocer.harcamabolustur.data.local.entities.GroupMemberEntity
import com.mustafakocer.harcamabolustur.data.local.entities.GroupRole
import com.mustafakocer.harcamabolustur.data.local.mapper.toDomainModel
import com.mustafakocer.harcamabolustur.data.local.mapper.toEntity
import com.mustafakocer.harcamabolustur.domain.model.Group
import com.mustafakocer.harcamabolustur.domain.model.GroupMember
import com.mustafakocer.harcamabolustur.domain.repository.GroupRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GroupRepositoryImpl @Inject constructor(
    private val groupDao: GroupDao,
    private val groupMemberDao: GroupMemberDao
) : GroupRepository {
    override fun getUserGroups(userId: String): Flow<PagingData<Group>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                prefetchDistance = 5,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                groupDao.getUserGroupsPagingSource(userId)
            }
        ).flow.map { pagingData ->
            pagingData.map { entity ->
                entity.toDomainModel()
            }
        }
    }

    override suspend fun getGroupById(groupId: String): Group? {
        return groupDao.getGroupById(groupId)?.toDomainModel()
    }

    override fun getGroupBuIdFlow(groupId: String): Flow<Group?> {
        return groupDao.getGroupByIdFlow(groupId).map { entity ->
            entity?.toDomainModel()
        }
    }

    override suspend fun createGroup(
        group: Group,
        currentUserId: String
    ): Result<String> {
        return try {
            // Grup kaydet
            val groupEntity = group.toEntity()
            groupDao.insertGroup(groupEntity)

            // Kendini admin olarak ekle
            val memberEntity = GroupMemberEntity(
                id = "${group.id}_${currentUserId}",
                groupId = group.id,
                userId = currentUserId,
                role = GroupRole.ADMIN,
                joinedAt = System.currentTimeMillis(),
                isActive = true,
                notificationsEnabled = true,
                needsSync = true
            )
            groupMemberDao.insertGroupMember(memberEntity)

            Result.success(group.id)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun updateGroup(group: Group): Result<Unit> {
        return try {
            groupDao.updateGroup(group.toEntity().copy(needsSync = true))
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun deleteGroup(
        groupId: String,
        userId: String
    ): Result<Unit> {
        return try {
            // Admin kontrolü
            val isAdmin = isUserAdminOfGroup(groupId, userId)
            if (!isAdmin) {
                return Result.failure(Exception("Only admin can delete group"))
            }

            groupDao.deactivateGroup(groupId)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun isUserMemberOfGroup(
        groupId: String,
        userId: String
    ): Boolean {
        return groupDao.isUserMemberOfGroup(groupId, userId) > 0
    }

    override suspend fun isUserAdminOfGroup(
        groupId: String,
        userId: String
    ): Boolean {
        return groupDao.isUserAdminOfGroup(groupId, userId) > 0
    }

    override suspend fun generateUniqueInviteCode(): String {
        var code: String
        do {
            code = generateRandomCode()
        } while (groupDao.isInviteCodeExists(code) > 0)
        return code
    }

    override fun getGroupMembers(groupId: String): Flow<List<GroupMember>> {
        // Todo: join with users - şimdilik basit
        return groupMemberDao.getGroupMembers(groupId).map { entities ->
            entities.map { it.toDomainModel() }
        }
    }

    override suspend fun addMemberToGroup(
        groupId: String,
        userId: String,
        role: String
    ): Result<Unit> {
        return try {
            val memberEntity = GroupMemberEntity(
                id = "${groupId}_$userId",
                groupId = groupId,
                userId = userId,
                role = GroupRole.valueOf(role),
                joinedAt = System.currentTimeMillis(),
                isActive = true,
                notificationsEnabled = true,
                needsSync = true
            )

            groupMemberDao.insertGroupMember(memberEntity)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun removeMemberToGroup(
        groupId: String,
        userId: String
    ): Result<Unit> {
        return try {
            groupMemberDao.leaveGroup(groupId = groupId, userId = userId)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun updateMemberRole(
        groupId: String,
        userId: String,
        role: String
    ): Result<Unit> {
        return try {
            when (role) {
                "ADMIN" -> groupMemberDao.promoteToAdmin(groupId, userId)
                "MEMBER" -> groupMemberDao.demoteToMember(groupId, userId)
            }
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }


    private fun generateRandomCode(): String {
        val chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"
        return (1..6).map { chars.random() }.joinToString("")
    }

}