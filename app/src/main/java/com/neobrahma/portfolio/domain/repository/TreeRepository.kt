package com.neobrahma.portfolio.domain.repository

import com.neobrahma.portfolio.domain.model.CompanyData
import kotlinx.coroutines.flow.Flow

interface TreeRepository {

    suspend fun getCompanies(): Flow<List<CompanyData>>

}