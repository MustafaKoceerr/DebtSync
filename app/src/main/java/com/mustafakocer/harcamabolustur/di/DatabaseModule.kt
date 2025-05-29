package com.mustafakocer.harcamabolustur.di

import android.content.Context
import androidx.room.Room
import com.mustafakocer.harcamabolustur.core.constants.DatabaseConstants
import com.mustafakocer.harcamabolustur.data.local.dao.DebtDao
import com.mustafakocer.harcamabolustur.data.local.dao.ExpenseDao
import com.mustafakocer.harcamabolustur.data.local.dao.ExpenseParticipantDao
import com.mustafakocer.harcamabolustur.data.local.dao.ExpensePayerDao
import com.mustafakocer.harcamabolustur.data.local.dao.GroupDao
import com.mustafakocer.harcamabolustur.data.local.dao.GroupInvitationDao
import com.mustafakocer.harcamabolustur.data.local.dao.GroupMemberDao
import com.mustafakocer.harcamabolustur.data.local.dao.RemoteKeysDao
import com.mustafakocer.harcamabolustur.data.local.dao.UserDao
import com.mustafakocer.harcamabolustur.data.local.database.DebtSyncDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDebtSyncDatabase(
        @ApplicationContext context: Context
    ): DebtSyncDatabase {
        return Room.databaseBuilder(
            context,
            DebtSyncDatabase::class.java,
            DatabaseConstants.DATABASE_NAME
        ).build()
    }

    // ============ ALL DAOs ============
    @Provides
    fun provideUserDao(database: DebtSyncDatabase): UserDao {
        return database.userDao()
    }

    @Provides
    fun provideGroupDao(database: DebtSyncDatabase): GroupDao {
        return database.groupDao()
    }

    @Provides
    fun provideGroupMemberDao(database: DebtSyncDatabase): GroupMemberDao {
        return database.groupMemberDao()
    }

    @Provides
    fun provideGroupInvitationDao(database: DebtSyncDatabase): GroupInvitationDao {
        return database.groupInvitationDao()
    }

    @Provides
    fun provideExpenseDao(database: DebtSyncDatabase): ExpenseDao {
        return database.expenseDao()
    }

    @Provides
    fun provideExpensePayerDao(database: DebtSyncDatabase): ExpensePayerDao {
        return database.expensePayerDao()
    }

    @Provides
    fun provideExpenseParticipantDao(database: DebtSyncDatabase): ExpenseParticipantDao {
        return database.expenseParticipantDao()
    }

    @Provides
    fun provideDebtDao(database: DebtSyncDatabase): DebtDao {
        return database.debtDao()
    }

    @Provides
    fun provideRemoteKeysDao(database: DebtSyncDatabase): RemoteKeysDao {
        return database.remoteKeysDao()
    }
}