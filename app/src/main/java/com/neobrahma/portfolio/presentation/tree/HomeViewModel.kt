package com.neobrahma.portfolio.presentation.tree

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neobrahma.portfolio.domain.model.CompanyData
import com.neobrahma.portfolio.domain.usecase.tree.GetCompaniesUseCase
import com.neobrahma.portfolio.presentation.tree.client.ClientMapper
import com.neobrahma.portfolio.presentation.tree.company.CompanyMapper
import com.neobrahma.portfolio.presentation.tree.companies.CompaniesMapper
import com.neobrahma.portfolio.presentation.tree.model.Tree
import com.neobrahma.portfolio.presentation.tree.project.ProjectClientMapper
import com.neobrahma.portfolio.presentation.tree.project.ProjectMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val companiesMapper: CompaniesMapper,
    private val companyMapper: CompanyMapper,
    private val projectMapper: ProjectMapper,
    private val clientMapper: ClientMapper,
    private val projectClientMapper: ProjectClientMapper,
    private val getCompaniesUseCase: GetCompaniesUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loader)
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    private val _uiStateCompany = MutableStateFlow<HomeUiState>(HomeUiState.Loader)
    val uiStateCompany: StateFlow<HomeUiState> = _uiStateCompany.asStateFlow()

    private val _uiStateProject = MutableStateFlow<HomeUiState>(HomeUiState.Loader)
    val uiStateProject: StateFlow<HomeUiState> = _uiStateProject.asStateFlow()

    private val _uiStateClient = MutableStateFlow<HomeUiState>(HomeUiState.Loader)
    val uiStateClient: StateFlow<HomeUiState> = _uiStateClient.asStateFlow()

    private val _uiStateProjectClient = MutableStateFlow<HomeUiState>(HomeUiState.Loader)
    val uiStateProjectClient: StateFlow<HomeUiState> = _uiStateProjectClient.asStateFlow()

    private val companiesUI = mutableListOf<CompanyData>()

    fun initHomeScreen() {
        viewModelScope.launch(Dispatchers.IO) {
            getCompaniesUseCase().collect{
                companiesUI.clear()
                companiesUI.addAll(it)
                _uiState.value = HomeUiState.DisplayTreePrimary(
                    companiesMapper.mapperToUI(companiesUI)
                )
            }
        }
    }

    fun initCompanyScreen(idCompany: Int = -1) {
        _uiStateCompany.value = HomeUiState.Loader
        viewModelScope.launch(Dispatchers.IO) {
            if (idCompany != -1) {
                val company = companiesUI.filter {
                    it.companyId == idCompany
                }[0]
                _uiStateCompany.value =
                    HomeUiState.DisplayTreePrimary(companyMapper.mapperToUi(company))
            }
        }
    }

    fun initProjectScreen(idCompany: Int = -1, idProject: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            if (idCompany != -1) {
                val company = companiesUI.filter {
                    it.companyId == idCompany
                }[0]

                val project = company.projects.filter { project ->
                    project.id == idProject
                }[0]

                _uiStateProject.value =
                    HomeUiState.DisplayTree(projectMapper.mapperProjectToUI(company, project))
            }
        }
    }

    fun initClientScreen(idCompany: Int = -1, clientId: Int = -1) {
        viewModelScope.launch(Dispatchers.IO) {
            if (idCompany != -1 && clientId != -1) {
                val company = companiesUI.filter {
                    it.companyId == idCompany
                }[0]
                val client = company.clients.filter {
                    it.clientId == clientId
                }[0]
                _uiStateClient.value = HomeUiState.DisplayTreePrimary(
                    clientMapper.mapperToUi(company, client)
                )
            }
        }
    }

    fun initProjectClientScreen(idCompany: Int = -1, clientId: Int, idProject: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            if (idCompany != -1) {
                val company = companiesUI.filter {
                    it.companyId == idCompany
                }[0]

                val client = company.clients.filter { client ->
                    client.clientId == clientId
                }[0]

                val project = client.projects.filter { project ->
                    project.id == idProject
                }[0]

                _uiStateProjectClient.value =
                    HomeUiState.DisplayTree(projectClientMapper.mapperProjectToUI(company, client, project))
            }
        }
    }
}

sealed class HomeUiState {
    object Loader : HomeUiState()
    class DisplayTreePrimary(val tree: List<Tree.PrimaryItem>) : HomeUiState()
    class DisplayTree(val tree: List<Tree>) : HomeUiState()
}