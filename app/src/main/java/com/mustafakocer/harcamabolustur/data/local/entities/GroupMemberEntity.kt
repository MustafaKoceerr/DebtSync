package com.mustafakocer.harcamabolustur.data.local.entities

import android.app.Notification
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters

@Entity(tableName = "group_members")
@TypeConverters(GroupRoleConverter::class)
data class GroupMemberEntity(
    @PrimaryKey
    val id: String,
    val groupId: String,
    val userId: String,
    val role: GroupRole, // Enum olarak kullanıyoruz
    val joinedAt: Long,
    val isActive: Boolean = true,
    val notificationsEnabled: Boolean = true,

    // Offline sync için
    val lastSyncedAt: Long = 0L,
    val needsSync: Boolean = false
)

enum class GroupRole {
    ADMIN, MEMBER
}

// TypeConverter for GroupRole
class GroupRoleConverter {
    @TypeConverter
    fun fromGroupRole(role: GroupRole): String {
        return role.name
    }

    @TypeConverter
    fun toGroupRole(role: String): GroupRole {
        return GroupRole.valueOf(role)
    }
}
