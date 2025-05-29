package com.mustafakocer.harcamabolustur.domain.repository

import androidx.room.Query
import com.mustafakocer.harcamabolustur.data.local.entities.UserRelationshipType
import com.mustafakocer.harcamabolustur.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    // Authentication
    suspend fun getCurrentUser(): User?
    fun getCurrentUserFlow(): Flow<User?>
    suspend fun saveCurrentUser(user: User)

    // User operations
    suspend fun getUserById(userId: String): User?
    suspend fun getUserByUsername(username: String): User?
    fun getUsersInGroup(groupId: String): Flow<List<User>>

    // Search (Firebase'den)
    suspend fun searchUsersByUsername(query: String): List<User>

    // Local cache management
    suspend fun addUserToLocalCache(user: User, relationshipType: String)
    suspend fun cleanupOldUsers()
}