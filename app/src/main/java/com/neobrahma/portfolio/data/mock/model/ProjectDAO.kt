package com.neobrahma.portfolio.data.mock.model

data class ProjectDAO(
    var id: Int,
    var name: String,
    var logo: String,
    var role: String,
    var context: String,
    var tasks: List<String>,
    var stacks: List<Int>
)
