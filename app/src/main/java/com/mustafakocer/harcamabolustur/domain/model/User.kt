package com.mustafakocer.harcamabolustur.domain.model

// ============ USER MODEL ============
data class User(
    val id: String,
    val email: String,
    val username: String, // @johndoe
    val displayName: String,
    val photoUrl: String? = null,
    val createdAt: Long = System.currentTimeMillis(),
    val relationshipType: UserRelationshipType = UserRelationshipType.GROUP_MEMBER
)

enum class UserRelationshipType {
    SELF,           // Kendim
    GROUP_MEMBER,   // Aynı grupta
    INVITED_BY_ME,  // Benim davet ettiğim
    INVITED_ME      // Beni davet eden
}