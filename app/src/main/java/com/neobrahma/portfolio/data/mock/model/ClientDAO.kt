package com.neobrahma.portfolio.data.mock.model

data class ClientDAO(
    val id: Int,
    val name: String,
    val logo: String,
    val date: String,
    val city: String,
    val projects: List<ProjectDAO>
)
