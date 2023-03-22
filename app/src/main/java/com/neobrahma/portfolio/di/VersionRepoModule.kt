package com.neobrahma.portfolio.di

import com.neobrahma.portfolio.data.VersionLocalDataSource
import com.neobrahma.portfolio.data.VersionRemoteDataSource
import com.neobrahma.portfolio.data.datastore.VersionLocalDataSourceImpl
import com.neobrahma.portfolio.data.repository.VersionRepositoryImpl
import com.neobrahma.portfolio.data.rest.VersionRemoteDataSourceImpl
import com.neobrahma.portfolio.domain.repository.VersionRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface VersionRepoModule {

    @Singleton
    @Binds
    fun bindVersionRepository(impl: VersionRepositoryImpl): VersionRepository

    @Singleton
    @Binds
    fun bindVersionLocalDataSource(impl: VersionLocalDataSourceImpl): VersionLocalDataSource

    @Singleton
    @Binds
    fun bindVersionRemoteDataSource(impl: VersionRemoteDataSourceImpl): VersionRemoteDataSource
}