package com.mustafakocer.harcamabolustur.domain.model


// ============ GROUP MEMBER MODEL ============
data class GroupMember(
    val id: String,
    val groupId: String,
    val userId: String,
    val user: User, // Join edilmiş user bilgisi
    val role: GroupRole,
    val joinedAt: Long,
    val notificationsEnabled: Boolean = true,
)

enum class GroupRole {
    ADMIN, MEMBER;
}