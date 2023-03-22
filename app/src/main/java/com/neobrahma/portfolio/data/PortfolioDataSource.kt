package com.neobrahma.portfolio.data

import com.neobrahma.portfolio.data.mock.model.CompanyDAO
import com.neobrahma.portfolio.domain.model.CategoryData
import com.neobrahma.portfolio.domain.model.CompanyData
import com.neobrahma.portfolio.domain.model.StackData
import kotlinx.coroutines.flow.Flow

interface PortfolioDataSource {

    suspend fun addAllStack(stacks: List<StackData>)

    suspend fun addAllCategory(categories: List<CategoryData>)

    suspend fun addAllCompany(companies: List<CompanyDAO>)

    suspend fun getCompanies(): Flow<List<CompanyData>>

}