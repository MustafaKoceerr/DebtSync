package com.mustafakocer.harcamabolustur.data.local.mapper

import com.mustafakocer.harcamabolustur.data.local.entities.GroupEntity
import com.mustafakocer.harcamabolustur.domain.model.Group

 fun GroupEntity.toDomainModel() = Group(
    id = id,
    name = name,
    description = description,
    photoUrl = photoUrl,
    inviteCode = inviteCode,
    createdBy = createdBy,
    createdAt = createdAt,
    currency = currency,
    memberCount = 0, // TODO: Calculate
    totalExpenses = 0.0 // TODO: Calculate
)

 fun Group.toEntity() = GroupEntity(
    id = id,
    name = name,
    description = description,
    photoUrl = photoUrl,
    inviteCode = inviteCode,
    createdBy = createdBy,
    createdAt = createdAt,
    currency = currency,
    isActive = true,
    needsSync = true
)