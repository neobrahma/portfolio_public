package com.neobrahma.portfolio.presentation.filter.filter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neobrahma.portfolio.domain.usecase.filter.GetCategoriesUseCase
import com.neobrahma.portfolio.domain.usecase.filter.UpdateStackStateUseCase
import com.neobrahma.portfolio.presentation.filter.filter.model.FilterUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FilterViewModel @Inject constructor(
    private val filterMapper: FilterMapper,
    getCategoriesUseCase: GetCategoriesUseCase,
    private val updateStackStateUseCase: UpdateStackStateUseCase
) : ViewModel() {

    val initFilter: Flow<List<FilterUi>> = getCategoriesUseCase().map { categories ->
        filterMapper.mapperCategoryToUI(categories)
    }

    fun updateStack(stackId: Int, isFavorite: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            updateStackStateUseCase(stackId, isFavorite)
        }
    }

}

