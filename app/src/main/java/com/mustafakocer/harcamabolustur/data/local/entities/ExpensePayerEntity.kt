package com.mustafakocer.harcamabolustur.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "expense_payers")
data class ExpensePayerEntity(
    @PrimaryKey
    val id: String,
    val expenseId: String,
    val userId: String, // Kim ne ödedi
    val amount: Double, // Ne kadar ödedi

    // Offline sync için
    val lastSyncedAt: Long = 0L,
    val needsSync: Boolean = false
)