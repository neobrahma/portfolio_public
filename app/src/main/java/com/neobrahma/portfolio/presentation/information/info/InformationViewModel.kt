package com.neobrahma.portfolio.presentation.information.info

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neobrahma.portfolio.domain.usecase.information.GetStackStatisticalUseCase
import com.neobrahma.portfolio.presentation.information.info.model.InformationUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InformationViewModel @Inject constructor(
    private val informationMapper: InformationMapper,
    private val getStackStatisticalUseCase: GetStackStatisticalUseCase
) : ViewModel() {

    private val _uiStateCompanies = MutableStateFlow<List<InformationUI>>(emptyList())
    val uiStateCompanies: StateFlow<List<InformationUI>> = _uiStateCompanies.asStateFlow()

    fun initInformationScreen() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = getStackStatisticalUseCase()
            _uiStateCompanies.value = informationMapper.mapperToUI(result)
        }
    }

}

