package com.mustafakocer.harcamabolustur.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mustafakocer.harcamabolustur.data.local.dao.DebtDao
import com.mustafakocer.harcamabolustur.data.local.dao.ExpenseDao
import com.mustafakocer.harcamabolustur.data.local.dao.ExpenseParticipantDao
import com.mustafakocer.harcamabolustur.data.local.dao.ExpensePayerDao
import com.mustafakocer.harcamabolustur.data.local.dao.GroupDao
import com.mustafakocer.harcamabolustur.data.local.dao.GroupInvitationDao
import com.mustafakocer.harcamabolustur.data.local.dao.GroupMemberDao
import com.mustafakocer.harcamabolustur.data.local.dao.RemoteKeysDao
import com.mustafakocer.harcamabolustur.data.local.dao.UserDao
import com.mustafakocer.harcamabolustur.data.local.entities.DebtEntity
import com.mustafakocer.harcamabolustur.data.local.entities.ExpenseEntity
import com.mustafakocer.harcamabolustur.data.local.entities.ExpenseParticipantEntity
import com.mustafakocer.harcamabolustur.data.local.entities.ExpensePayerEntity
import com.mustafakocer.harcamabolustur.data.local.entities.GroupEntity
import com.mustafakocer.harcamabolustur.data.local.entities.GroupInvitationEntity
import com.mustafakocer.harcamabolustur.data.local.entities.GroupMemberEntity
import com.mustafakocer.harcamabolustur.data.local.entities.GroupRoleConverter
import com.mustafakocer.harcamabolustur.data.local.entities.InvitationStatusConverter
import com.mustafakocer.harcamabolustur.data.local.entities.RemoteKeysEntity
import com.mustafakocer.harcamabolustur.data.local.entities.UserEntity
import com.mustafakocer.harcamabolustur.data.local.entities.UserRelationshipTypeConverter


@Database(
    entities = [
        UserEntity::class,
        GroupEntity::class,
        GroupMemberEntity::class,
        GroupInvitationEntity::class,
        ExpenseEntity::class,
        ExpensePayerEntity::class,
        ExpenseParticipantEntity::class,
        DebtEntity::class,
        RemoteKeysEntity::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(
    GroupRoleConverter::class,
    InvitationStatusConverter::class,
    UserRelationshipTypeConverter::class
)
abstract class DebtSyncDatabase : RoomDatabase() {

    // DAOs
    abstract fun userDao(): UserDao
    abstract fun groupDao(): GroupDao
    abstract fun groupMemberDao(): GroupMemberDao
    abstract fun groupInvitationDao(): GroupInvitationDao
    abstract fun expenseDao(): ExpenseDao
    abstract fun expensePayerDao(): ExpensePayerDao
    abstract fun expenseParticipantDao(): ExpenseParticipantDao
    abstract fun debtDao(): DebtDao
    abstract fun remoteKeysDao(): RemoteKeysDao
}