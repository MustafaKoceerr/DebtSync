package com.mustafakocer.harcamabolustur.di

import com.mustafakocer.harcamabolustur.data.local.repository.GroupRepositoryImpl
import com.mustafakocer.harcamabolustur.domain.repository.GroupRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindGroupRepository(
        groupRepositoryImpl: GroupRepositoryImpl
    ): GroupRepository

}