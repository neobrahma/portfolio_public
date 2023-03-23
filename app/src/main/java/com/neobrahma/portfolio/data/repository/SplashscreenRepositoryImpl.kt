package com.neobrahma.portfolio.data.repository

import com.neobrahma.portfolio.data.CheckoutPortfolioDataSource
import com.neobrahma.portfolio.data.SavePortfolioDataSource
import com.neobrahma.portfolio.data.VersionLocalDataSource
import com.neobrahma.portfolio.data.VersionRemoteDataSource
import com.neobrahma.portfolio.domain.repository.SplashscreenRepository
import javax.inject.Inject

class SplashscreenRepositoryImpl @Inject constructor(
    private val checkoutPortfolioDataSource: CheckoutPortfolioDataSource,
    private val savePortfolioDataSource: SavePortfolioDataSource,
    private val localDataSource: VersionLocalDataSource,
    private val remoteDataSource: VersionRemoteDataSource,
) : SplashscreenRepository {

    override suspend fun checkoutPortfolio() {
        val portfolio = checkoutPortfolioDataSource.checkoutPortfolio()

        savePortfolioDataSource.addAllStack(portfolio.stacks)
        savePortfolioDataSource.addAllCategory(portfolio.categories)
        savePortfolioDataSource.addAllCompany(portfolio.companies)
    }

    override suspend fun getLocalVersion(): Int {
        return localDataSource.getVersion()
    }

    override suspend fun getRemoteVersion(): Int {
        return remoteDataSource.getVersion()
    }

    override suspend fun updateVersion() {
        localDataSource.updateVersion()
    }

}