data class GroupInvitation(
    val id: String,
    val groupId: String,
    val invitedUsername: String,
    val invitedByUserId: String,
    val status: InvitationStatus, // Enum olarak
    val createdAt: Long,
    val expiredAt: Long
)

enum class InvitationStatus {
    PENDING,
    ACCEPTED,
    REJECTED,
    EXPIRED
}