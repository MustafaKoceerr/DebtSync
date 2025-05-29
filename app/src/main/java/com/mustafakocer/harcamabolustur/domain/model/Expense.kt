package com.mustafakocer.harcamabolustur.domain.model

// ============ EXPENSE MODELS ============
data class Expense(
    val id: String,
    val groupId: String,
    val title: String,
    val totalAmount: Double,
    val currency: String,
    val createdBy: String,
    val createdByUser: User, // Join edilmiş user
    val createdAt: Long,
    val category: String? = null,
    val paidByUsers: List<User> = emptyList(), // Kim ödedi (UI için)
    val participantCount: Int = 0 // Kaç kişi katıldı (UI için)
)