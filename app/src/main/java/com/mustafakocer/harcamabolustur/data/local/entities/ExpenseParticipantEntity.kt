package com.mustafakocer.harcamabolustur.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "expense_participants")
data class ExpenseParticipantEntity(
    @PrimaryKey
    val id: String,
    val expenseId: String,
    val userId: String, // Kim borçlu/katılımcı
    val amount: Double, // Ne kadar borçlu (payı)

    // Offline sync için
    val lastSyncedAt: Long = 0L,
    val needsSync: Boolean = false
)