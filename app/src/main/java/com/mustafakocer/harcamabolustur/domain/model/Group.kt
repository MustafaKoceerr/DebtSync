package com.mustafakocer.harcamabolustur.domain.model

import android.icu.util.Currency

// ============ GROUP MODEL ============
data class Group(
    val id: String,
    val name: String,
    val description: String? = null,
    val photoUrl: String? = null,
    val inviteCode: String,
    val createdBy: String,
    val createdAt: Long = System.currentTimeMillis(),
    val currency: String = "TRY",
    val memberCount: Int = 0, // UI için
    val totalExpenses: Double = 0.0,
)
