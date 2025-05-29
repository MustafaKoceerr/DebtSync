package com.mustafakocer.harcamabolustur.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mustafakocer.harcamabolustur.data.local.entities.DebtEntity
import kotlinx.coroutines.flow.Flow

// ============ DEBT DAO ============
@Dao
interface DebtDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDebt(debt: DebtEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDebts(debts: List<DebtEntity>)

    @Query("SELECT * FROM debts WHERE groupId = :groupId AND isSettled = 0")
    fun getGroupDebts(groupId: String): Flow<List<DebtEntity>>

    @Query("UPDATE debts SET isSettled = 1, settledAt = :settledTime WHERE id = :debtId")
    suspend fun settleDebt(debtId: String, settledTime: Long)
}