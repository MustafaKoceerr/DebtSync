package com.mustafakocer.harcamabolustur.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mustafakocer.harcamabolustur.data.local.entities.ExpenseParticipantEntity
import kotlinx.coroutines.flow.Flow

// ============ EXPENSE PARTICIPANT DAO ============
@Dao
interface ExpenseParticipantDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertParticipant(participant: ExpenseParticipantEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertParticipants(participants: List<ExpenseParticipantEntity>)

    @Query("SELECT * FROM expense_participants WHERE expenseId = :expenseId")
    fun getExpenseParticipants(expenseId: String): Flow<List<ExpenseParticipantEntity>>
}