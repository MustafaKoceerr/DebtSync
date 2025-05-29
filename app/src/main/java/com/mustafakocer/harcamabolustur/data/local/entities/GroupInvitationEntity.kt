package com.mustafakocer.harcamabolustur.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters

@Entity(tableName = "group_invitations")
@TypeConverters(InvitationStatusConverter::class)
data class GroupInvitationEntity(
    @PrimaryKey
    val id: String,
    val groupId: String,
    val invitedUsername: String, // @johndoe
    val invitedByUserId: String, // Admin ID
    val status: InvitationStatus,
    val createdAt: Long,
    val expiredAt: Long, // 7 gün sonra geçersiz
    val respondedAt: Long? = null, // Ne zaman cevaplandı

    // Offline sync için
    val lastSyncedAt: Long = 0L,
    val needsSync: Boolean = false
)

enum class InvitationStatus {
    PENDING,
    ACCEPTED,
    REJECTED,
    EXPIRED
}

// TypeConverter for InvitationStatus
class InvitationStatusConverter {
    @TypeConverter
    fun fromInvitationStatus(status: InvitationStatus): String {
        return status.name
    }

    @TypeConverter
    fun toInvitationStatus(status: String): InvitationStatus {
        return InvitationStatus.valueOf(status)
    }
}