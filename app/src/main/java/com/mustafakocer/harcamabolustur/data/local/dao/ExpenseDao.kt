package com.mustafakocer.harcamabolustur.data.local.dao


import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.mustafakocer.harcamabolustur.data.local.entities.ExpenseEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ExpenseDao {

    // ============ PAGING3 - Grup harcamaları ============
    @Query("""
        SELECT * FROM expenses 
        WHERE groupId = :groupId AND isActive = 1 
        ORDER BY createdAt DESC
    """)
    fun getGroupExpensesPagingSource(groupId: String): PagingSource<Int, ExpenseEntity>

    // ============ INSERT/UPDATE ============
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExpense(expense: ExpenseEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExpenses(expenses: List<ExpenseEntity>)

    @Update
    suspend fun updateExpense(expense: ExpenseEntity)

    // ============ BASIC QUERIES ============
    @Query("SELECT * FROM expenses WHERE id = :expenseId AND isActive = 1")
    suspend fun getExpenseById(expenseId: String): ExpenseEntity?

    @Query("SELECT * FROM expenses WHERE id = :expenseId AND isActive = 1")
    fun getExpenseByIdFlow(expenseId: String): Flow<ExpenseEntity?>

    // Grup toplam harcama
    @Query("SELECT SUM(totalAmount) FROM expenses WHERE groupId = :groupId AND isActive = 1")
    fun getGroupTotalExpenses(groupId: String): Flow<Double?>

    // Kullanıcının harcama sayısı
    @Query("SELECT COUNT(*) FROM expenses WHERE groupId = :groupId AND createdBy = :userId AND isActive = 1")
    fun getUserExpenseCount(groupId: String, userId: String): Flow<Int>

    // ============ SYNC OPERATIONS ============
    @Query("SELECT * FROM expenses WHERE needsSync = 1")
    suspend fun getExpensesNeedingSync(): List<ExpenseEntity>

    @Query("UPDATE expenses SET needsSync = 0, lastSyncedAt = :syncTime WHERE id = :expenseId")
    suspend fun markExpenseAsSynced(expenseId: String, syncTime: Long)

    // ============ DELETE/CLEANUP ============
    @Query("UPDATE expenses SET isActive = 0 WHERE id = :expenseId")
    suspend fun deactivateExpense(expenseId: String)

    @Query("DELETE FROM expenses WHERE groupId = :groupId")
    suspend fun deleteGroupExpenses(groupId: String)

    @Query("DELETE FROM expenses")
    suspend fun clearAllExpenses()
}