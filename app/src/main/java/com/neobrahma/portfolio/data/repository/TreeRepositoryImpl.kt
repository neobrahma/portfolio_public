package com.neobrahma.portfolio.data.repository

import com.neobrahma.portfolio.data.PortfolioDataSource
import com.neobrahma.portfolio.domain.model.CompanyData
import com.neobrahma.portfolio.domain.repository.TreeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TreeRepositoryImpl @Inject constructor(
    private val portfolioDataSource: PortfolioDataSource,
) : TreeRepository {

    override suspend fun getCompanies(): Flow<List<CompanyData>> {
        return portfolioDataSource.getCompanies()
    }

}