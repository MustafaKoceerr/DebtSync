package com.mustafakocer.harcamabolustur.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

// Paging3 RemoteMediator için remote keys
@Entity(tableName = "remote_keys")
data class RemoteKeysEntity(
    @PrimaryKey
    val entityId: String, // Hangi entity'nin key'i (group_id, expense_id vs)
    val entityType: String, // "groups", "expenses", "debts"
    val prevKey: String?,
    val nextKey: String?,
    val lastRefresh: Long // En son ne zaman refresh edildi
)