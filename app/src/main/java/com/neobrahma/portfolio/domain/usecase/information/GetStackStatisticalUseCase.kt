package com.neobrahma.portfolio.domain.usecase.information

import com.neobrahma.portfolio.domain.model.InformationData
import com.neobrahma.portfolio.domain.repository.InformationRepository
import javax.inject.Inject

class GetStackStatisticalUseCase @Inject constructor(
    private val repository: InformationRepository
) {

    suspend operator fun invoke() : InformationData {
        return repository.getStackStatistical()
    }
}