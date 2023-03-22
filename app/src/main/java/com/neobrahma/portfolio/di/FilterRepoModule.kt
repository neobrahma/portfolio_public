package com.neobrahma.portfolio.di

import com.neobrahma.portfolio.data.FilterDataSource
import com.neobrahma.portfolio.data.database.FilterLocalDataSourceImpl
import com.neobrahma.portfolio.data.repository.FilterRepositoryImpl
import com.neobrahma.portfolio.domain.repository.FilterRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface FilterRepoModule {

    @Singleton
    @Binds
    fun bindFilterRepository(impl: FilterRepositoryImpl): FilterRepository

    @Singleton
    @Binds
    fun bindFilterDataSource(impl: FilterLocalDataSourceImpl): FilterDataSource

}
