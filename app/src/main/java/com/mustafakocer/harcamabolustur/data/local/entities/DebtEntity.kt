package com.mustafakocer.harcamabolustur.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "debts")
data class DebtEntity(
    @PrimaryKey
    val id: String,
    val groupId: String,
    val expenseId: String, // Hangi harcamadan doğdu
    val debtorId: String, // Kim borçlu
    val creditorId: String, // Kim alacaklı
    val amount: Double, // Borç miktarı
    val currency: String, // "TRY"
    val createdAt: Long,
    val isSettled: Boolean = false, // Ödendi mi?
    val settledAt: Long? = null,//Ne zaman ödendi

    // Offline sync için
    val lastSyncedAt: Long = 0L,
    val needsSync: Boolean = false
)