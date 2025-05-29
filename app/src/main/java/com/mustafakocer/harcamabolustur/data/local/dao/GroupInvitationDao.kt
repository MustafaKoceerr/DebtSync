package com.mustafakocer.harcamabolustur.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mustafakocer.harcamabolustur.data.local.entities.GroupInvitationEntity
import com.mustafakocer.harcamabolustur.data.local.entities.InvitationStatus
import kotlinx.coroutines.flow.Flow

// ============ GROUP INVITATION DAO ============
@Dao
interface GroupInvitationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertInvitation(invitation: GroupInvitationEntity)

    @Query("SELECT * FROM group_invitations WHERE invitedUsername = :username AND status = 'PENDING'")
    fun getPendingInvitations(username: String): Flow<List<GroupInvitationEntity>>

    @Query("UPDATE group_invitations SET status = :status WHERE id = :invitationId")
    suspend fun updateInvitationStatus(invitationId: String, status: InvitationStatus)
}
