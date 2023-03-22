package com.neobrahma.portfolio.di

import com.neobrahma.portfolio.data.PortfolioDataSource
import com.neobrahma.portfolio.data.TreeDataSource
import com.neobrahma.portfolio.data.database.PortfolioLocalDataSourceImpl
import com.neobrahma.portfolio.data.mock.MockDataSourceImpl
import com.neobrahma.portfolio.data.repository.PortfolioRepositoryImpl
import com.neobrahma.portfolio.data.repository.TreeRepositoryImpl
import com.neobrahma.portfolio.domain.repository.PortfolioRepository
import com.neobrahma.portfolio.domain.repository.TreeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface PortfolioRepoModule {

    @Singleton
    @Binds
    fun bindPortfolioRepository(impl: PortfolioRepositoryImpl): PortfolioRepository

    @Singleton
    @Binds
    fun bindPortfolioDataSource(impl: PortfolioLocalDataSourceImpl): PortfolioDataSource
}