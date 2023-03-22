package com.neobrahma.portfolio.domain.model

data class ClientData(
    val clientId: Int,
    val name: String,
    val logo: String,
    val date: String,
    val city: String,
    val projects: List<ProjectData>
)
