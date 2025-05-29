package com.mustafakocer.harcamabolustur.data.local.mapper

import com.mustafakocer.harcamabolustur.data.local.entities.GroupMemberEntity
import com.mustafakocer.harcamabolustur.domain.model.GroupMember
import com.mustafakocer.harcamabolustur.domain.model.GroupRole
import com.mustafakocer.harcamabolustur.domain.model.User

 fun GroupMemberEntity.toDomainModel() = GroupMember(
    id = id,
    groupId = groupId,
    userId = userId,
    user = User( // Dummy user - TODO: Join
        id = userId,
        email = "dummy@example.com",
        username = "@dummy",
        displayName = "User $userId"
    ),
    role = GroupRole.valueOf(role.name),
    joinedAt = joinedAt,
    notificationsEnabled = notificationsEnabled
)