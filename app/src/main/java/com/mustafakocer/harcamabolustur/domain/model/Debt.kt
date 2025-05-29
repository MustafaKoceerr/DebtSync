package com.mustafakocer.harcamabolustur.domain.model

data class Debt(
    val id: String,
    val groupId: String,
    val debtor: User, // kim borçlu
    val creditor: User, // Kim alacaklı
    val amount: Double,
    val currency: String,
    val expenseTitle: String, // hangi harcamadan
    val isSettled: Boolean = false
)