package com.mustafakocer.harcamabolustur.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mustafakocer.harcamabolustur.data.local.entities.ExpensePayerEntity
import kotlinx.coroutines.flow.Flow

// ============ EXPENSE PAYER DAO ============
@Dao
interface ExpensePayerDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPayer(payer: ExpensePayerEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPayers(payers: List<ExpensePayerEntity>)

    @Query("SELECT * FROM expense_payers WHERE expenseId = :expenseId")
    fun getExpensePayers(expenseId: String): Flow<List<ExpensePayerEntity>>
}