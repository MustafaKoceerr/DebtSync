package com.mustafakocer.harcamabolustur.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "group_table") // "groups" yerine "group_table"
data class GroupEntity(
    @PrimaryKey
    val id: String,
    val name: String, // "Ev Arkadaşları"
    val description: String?,
    val photoUrl: String?, // Grup fotoğrafı
    val inviteCode: String, // "ABC123" - 6 haneli unique kod
    val createdBy: String, // User ID (ilk admin)
    val createdAt: Long,
    val currency: String, // "TRY", "USD", "EUR"
    val isActive: Boolean = true,

    // Offline sync için
    val lastSyncedAt: Long = 0L,
    val needsSync: Boolean = false
)