package com.mustafakocer.harcamabolustur.domain.repository

import androidx.paging.PagingData
import com.mustafakocer.harcamabolustur.domain.model.Expense
import com.mustafakocer.harcamabolustur.domain.model.ExpenseDetail
import com.mustafakocer.harcamabolustur.domain.model.Group
import kotlinx.coroutines.flow.Flow

interface ExpenseRepository {

    // Paging3 - Grup Harcamaları
    fun getGroupExpenses(groupId: String): Flow<PagingData<Expense>>

    // Expense operations
    suspend fun getExpenseById(expenseId: String): ExpenseDetail?
    fun getExpenseByIdFlow(expenseId: String): Flow<ExpenseDetail?>
    suspend fun createExpense(expense: ExpenseDetail): Result<String>
    suspend fun updateExpense(expense: ExpenseDetail): Result<Unit>
    suspend fun deleteExpense(expenseId: String, userId: String): Result<Unit>

    // Statistics
    fun getGroupTotalExpenses(groupId: String): Flow<Double>
    fun getUserExpenseCount(groupId: String, userId: String): Flow<Int>
}