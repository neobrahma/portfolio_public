package com.neobrahma.portfolio.di

import com.neobrahma.portfolio.data.CheckoutPortfolioDataSource
import com.neobrahma.portfolio.data.SavePortfolioDataSource
import com.neobrahma.portfolio.data.VersionLocalDataSource
import com.neobrahma.portfolio.data.VersionRemoteDataSource
import com.neobrahma.portfolio.data.database.SavePortfolioLocalDataSourceImpl
import com.neobrahma.portfolio.data.datastore.VersionLocalDataSourceImpl
import com.neobrahma.portfolio.data.mock.CheckoutPortfolioDataSourceImpl
import com.neobrahma.portfolio.data.repository.SplashscreenRepositoryImpl
import com.neobrahma.portfolio.data.rest.VersionRemoteDataSourceImpl
import com.neobrahma.portfolio.domain.repository.SplashscreenRepository
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
    fun bindPortfolioRepository(impl: SplashscreenRepositoryImpl): SplashscreenRepository

    @Singleton
    @Binds
    fun bindCheckoutPortfolioDataSource(impl: CheckoutPortfolioDataSourceImpl): CheckoutPortfolioDataSource

    @Singleton
    @Binds
    fun bindSavePortfolioDataSource(impl: SavePortfolioLocalDataSourceImpl): SavePortfolioDataSource

    @Singleton
    @Binds
    fun bindVersionLocalDataSource(impl: VersionLocalDataSourceImpl): VersionLocalDataSource

    @Singleton
    @Binds
    fun bindVersionRemoteDataSource(impl: VersionRemoteDataSourceImpl): VersionRemoteDataSource
}