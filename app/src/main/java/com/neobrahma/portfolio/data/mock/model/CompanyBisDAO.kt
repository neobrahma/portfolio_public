package com.neobrahma.portfolio.data.mock.model

data class CompanyBisDAO(
    val companyId: Int,
    val name: String,
    val logo: String,
    val date: String,
    val city: String,
    val clients: List<ClientDAO>,
    val projects: List<ProjectBisDAO>
)