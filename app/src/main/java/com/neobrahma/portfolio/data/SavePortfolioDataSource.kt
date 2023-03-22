package com.neobrahma.portfolio.data

import com.neobrahma.portfolio.data.mock.model.CategoryDAO
import com.neobrahma.portfolio.data.mock.model.CompanyDAO
import com.neobrahma.portfolio.data.mock.model.StackDAO

interface SavePortfolioDataSource {

    suspend fun addAllStack(stacks: List<StackDAO>)

    suspend fun addAllCategory(categories: List<CategoryDAO>)

    suspend fun addAllCompany(companies: List<CompanyDAO>)

}