package com.mustafakocer.harcamabolustur.domain.model

// ============ DEBT MODEL ============
data class Debt(
    val id: String,
    val groupId: String,
    val debtor: User, // Kim borçlu
    val creditor: User, // Kim alacaklı
    val amount: Double,
    val currency: String,
    val expenseTitle: String, // Hangi harcamadan
    val isSettled: Boolean = false
)