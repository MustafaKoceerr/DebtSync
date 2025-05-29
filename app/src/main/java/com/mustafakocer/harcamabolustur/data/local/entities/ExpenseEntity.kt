package com.mustafakocer.harcamabolustur.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "expenses")
data class ExpenseEntity(
    @PrimaryKey
    val id: String,
    val groupId: String,
    val title: String, // Market Alışverişi
    val description: String?,
    val totalAmount: Double, // 300.0 (toplam harcama)
    val currency: String, // "TRY"
    val category: String?, // "Yemek", "Ulaşım"
    val receiptPhotoUrl: String?, // Nullable - isteğe bağlı
    val createdAt: Long,
    val createdBy: String, // User ID
    val isActive: Boolean = true,

    // Offline sync için
    val lastSyncedAt: Long = 0L,
    val needsSync: Boolean = false
)