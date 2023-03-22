package com.neobrahma.portfolio.data

import com.neobrahma.portfolio.domain.model.CompanyData
import kotlinx.coroutines.flow.Flow

interface TreeDataSource {
    suspend fun getCompanies(): Flow<List<CompanyData>>
}