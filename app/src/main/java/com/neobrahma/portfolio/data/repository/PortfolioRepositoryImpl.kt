package com.neobrahma.portfolio.data.repository

import com.neobrahma.portfolio.data.PortfolioDataSource
import com.neobrahma.portfolio.data.TreeDataSource
import com.neobrahma.portfolio.domain.repository.PortfolioRepository
import javax.inject.Inject

class PortfolioRepositoryImpl @Inject constructor(
    private val portfolioDataSource: PortfolioDataSource,
    private val mock: TreeDataSource
) : PortfolioRepository {

    override suspend fun checkoutPortfolio() {
        portfolioDataSource.addAllStack(
            mock.getStacks()
        )

        portfolioDataSource.addAllCategory(
            mock.getCategories()
        )

        portfolioDataSource.addAllCompany(
            mock.getCompanies()
        )
    }
}