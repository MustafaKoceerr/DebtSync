package com.mustafakocer.harcamabolustur.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey
    val id: String, // Firebase UID
    val email: String,
    val username: String, // @johndoe (unique)
    val displayName: String,
    val photoUrl: String?,
    val createdAt: Long,
    val isActive: Boolean = true,

    // İLİŞKİ BİLGİSİ - Neden local'de?
    val relationshipType: UserRelationshipType, // Bu kullanıcı neden local'de?
    val addedAt: Long, // Ne zaman eklendi

    // Offline sync için
    val lastSyncedAt: Long = 0L,
    val needsSync: Boolean = false
)

enum class UserRelationshipType {
    SELF,           // Kendim
    GROUP_MEMBER,   // Aynı grupta
    INVITED_BY_ME,  // Benim davet ettiğim
    INVITED_ME      // Beni davet eden
}

// TypeConverter for UserRelationshipType
class UserRelationshipTypeConverter {
    @TypeConverter
    fun fromUserRelationshipType(type: UserRelationshipType): String {
        return type.name
    }

    @TypeConverter
    fun toUserRelationshipType(type: String): UserRelationshipType {
        return UserRelationshipType.valueOf(type)
    }
}