package com.neobrahma.portfolio.data.repository

import com.neobrahma.portfolio.data.InformationDataSource
import com.neobrahma.portfolio.domain.model.CompanyData
import com.neobrahma.portfolio.domain.model.InformationData
import com.neobrahma.portfolio.domain.repository.InformationRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class InformationRepositoryImpl @Inject constructor(
    private val informationDataSource: InformationDataSource,
) : InformationRepository {

    override suspend fun getStackStatistical(): InformationData {
        return informationDataSource.getStackStatistical()
    }

}