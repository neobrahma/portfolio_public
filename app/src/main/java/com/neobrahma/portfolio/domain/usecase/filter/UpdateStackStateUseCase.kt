package com.neobrahma.portfolio.domain.usecase.filter

import com.neobrahma.portfolio.domain.repository.FilterRepository
import javax.inject.Inject

class UpdateStackStateUseCase @Inject constructor(
    private val repository: FilterRepository
) {
    suspend operator fun invoke(stackId: Int, isFavorite: Boolean) {
        return repository.updateStack(stackId, isFavorite)
    }
}