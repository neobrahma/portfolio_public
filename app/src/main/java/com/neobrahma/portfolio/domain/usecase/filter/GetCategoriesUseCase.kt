package com.neobrahma.portfolio.domain.usecase.filter

import com.neobrahma.portfolio.domain.model.CategoryData
import com.neobrahma.portfolio.domain.repository.FilterRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(
    private val repository: FilterRepository
) {
    operator fun invoke(): Flow<List<CategoryData>> {
        return repository.getItemsFilter()
    }
}