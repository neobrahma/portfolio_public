package com.neobrahma.portfolio.domain.model

data class CompanyData(
    val companyId: Int,
    val name: String,
    val logo: String,
    val date: String,
    val city: String,
    val clients: List<ClientData>,
    val projects: List<ProjectData>
)
