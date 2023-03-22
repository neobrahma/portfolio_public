package com.neobrahma.portfolio.presentation.tree

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neobrahma.portfolio.domain.model.CompanyData
import com.neobrahma.portfolio.domain.usecase.tree.GetCompaniesUseCase
import com.neobrahma.portfolio.presentation.tree.client.ClientMapper
import com.neobrahma.portfolio.presentation.tree.client.ClientUiState
import com.neobrahma.portfolio.presentation.tree.company.CompanyMapper
import com.neobrahma.portfolio.presentation.tree.companies.CompaniesMapper
import com.neobrahma.portfolio.presentation.tree.companies.CompaniesUiState
import com.neobrahma.portfolio.presentation.tree.company.CompanyUiState
import com.neobrahma.portfolio.presentation.tree.model.Tree
import com.neobrahma.portfolio.presentation.tree.project.ProjectClientMapper
import com.neobrahma.portfolio.presentation.tree.project.ProjectMapper
import com.neobrahma.portfolio.presentation.tree.project.ProjectUiState
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

    private val _uiStateCompanies = MutableStateFlow<CompaniesUiState>(CompaniesUiState.DisplayLoader)
    val uiStateCompanies: StateFlow<CompaniesUiState> = _uiStateCompanies.asStateFlow()

    private val _uiStateCompany = MutableStateFlow<CompanyUiState>(CompanyUiState.DisplayLoader)
    val uiStateCompany: StateFlow<CompanyUiState> = _uiStateCompany.asStateFlow()

    private val _uiStateProject = MutableStateFlow<ProjectUiState>(ProjectUiState.DisplayLoader)
    val uiStateProject: StateFlow<ProjectUiState> = _uiStateProject.asStateFlow()

    private val _uiStateClient = MutableStateFlow<ClientUiState>(ClientUiState.DisplayLoader)
    val uiStateClient: StateFlow<ClientUiState> = _uiStateClient.asStateFlow()

    private val _uiStateProjectClient = MutableStateFlow<ProjectUiState>(ProjectUiState.DisplayLoader)
    val uiStateProjectClient: StateFlow<ProjectUiState> = _uiStateProjectClient.asStateFlow()

    private val companiesUI = mutableListOf<CompanyData>()

    fun initHomeScreen() {
        viewModelScope.launch(Dispatchers.IO) {
            getCompaniesUseCase().collect{
                companiesUI.clear()
                companiesUI.addAll(it)
                _uiStateCompanies.value = CompaniesUiState.DisplayTree(
                    companiesMapper.mapperToUI(companiesUI)
                )
            }
        }
    }

    fun initCompanyScreen(idCompany: Int = -1) {
        _uiStateCompany.value = CompanyUiState.DisplayLoader
        viewModelScope.launch(Dispatchers.IO) {
            if (idCompany != -1) {
                val company = companiesUI.filter {
                    it.companyId == idCompany
                }[0]
                _uiStateCompany.value =
                    CompanyUiState.DisplayTree(companyMapper.mapperToUi(company))
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
                    ProjectUiState.DisplayTree(projectMapper.mapperProjectToUI(company, project))
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
                _uiStateClient.value = ClientUiState.DisplayTree(
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
                    ProjectUiState.DisplayTree(projectClientMapper.mapperProjectToUI(company, client, project))
            }
        }
    }
}