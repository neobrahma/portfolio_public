package com.neobrahma.portfolio.data

import com.neobrahma.portfolio.data.mock.model.CompanyDAO
import com.neobrahma.portfolio.domain.model.CategoryData
import com.neobrahma.portfolio.domain.model.CompanyData
import com.neobrahma.portfolio.domain.model.StackData

interface TreeDataSource {
    fun getCompanies(): List<CompanyDAO>
    fun getCompanyBy(idCompany: Int): CompanyData
    fun getProjectBy(idCompany: Int, idProject: Int): CompanyData
    fun getCategories(): List<CategoryData>
    fun getStacks(): List<StackData>
}