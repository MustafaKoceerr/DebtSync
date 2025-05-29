package com.mustafakocer.harcamabolustur.domain.model

import android.icu.util.Currency
import retrofit2.http.Url

// Expense detail (create/edit için)
data class ExpenseDetail(
    val id: String,
    val groupId: String,
    val title: String,
    val description: String? = null,
    val totalAmount: Double,
    val currency: String,
    val category: String? = null,
    val receiptPhotoUrl: String? = null,
    val createdBy: String,
    val createdAt: Long = System.currentTimeMillis(),
    val payers: List<ExpensePayer> = emptyList(),
    val participants: List<ExpenseParticipant> = emptyList()
)

data class ExpensePayer(
    val userId: String,
    val user: User,
    val amount: Double
)

data class ExpenseParticipant(
    val userId: String,
    val user: User,
    val amount: Double
)