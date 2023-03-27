package com.neobrahma.portfolio.domain.usecase.tree

import com.neobrahma.portfolio.domain.model.CompanyData
import com.neobrahma.portfolio.domain.repository.TreeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCompaniesUseCase @Inject constructor(
    private val repository: TreeRepository
) {

    suspend operator fun invoke(): Flow<List<CompanyData>> {
        return repository.getCompanies()
    }
}