package com.mustafakocer.harcamabolustur.presentation.groups

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.mustafakocer.harcamabolustur.domain.model.Group
import com.mustafakocer.harcamabolustur.domain.repository.GroupRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GroupsViewModel @Inject constructor(
    private val groupRepository: GroupRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(GroupsUiState())
    val uiState: StateFlow<GroupsUiState> = _uiState.asStateFlow()

    // TODO: Get current user ID from UserRepository
    private val currentUserId = "dummy_user_id"
    val groups: Flow<PagingData<Group>> = groupRepository
        .getUserGroups(currentUserId)
        .cachedIn(viewModelScope)

    fun createTestGroup() {
        viewModelScope.launch {
            val testGroup = Group(
                id = "group_${System.currentTimeMillis()}",
                name = "Test Group ${System.currentTimeMillis() % 100}",
                description = "Test açıklaması",
                photoUrl = null,
                inviteCode = generateRandomCode(),
                createdBy = currentUserId,
                currency = "TRY"
            )
            groupRepository.createGroup(testGroup, currentUserId)
        }
    }

    private fun generateRandomCode(): String {
        val chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"
        return (1..6).map { chars.random() }.joinToString("")
    }
}