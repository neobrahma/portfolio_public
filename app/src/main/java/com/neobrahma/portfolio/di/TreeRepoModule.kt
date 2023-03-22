package com.neobrahma.portfolio.di

import com.neobrahma.portfolio.data.TreeDataSource
import com.neobrahma.portfolio.data.database.TreeLocalDataSourceImpl
import com.neobrahma.portfolio.data.repository.TreeRepositoryImpl
import com.neobrahma.portfolio.domain.repository.TreeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface TreeRepoModule {

    @Singleton
    @Binds
    fun bindTreeRepository(impl: TreeRepositoryImpl): TreeRepository

    @Singleton
    @Binds
    fun bindTreeDataSource(impl: TreeLocalDataSourceImpl): TreeDataSource
}