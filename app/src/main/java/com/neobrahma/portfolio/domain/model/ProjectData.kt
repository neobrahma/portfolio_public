package com.neobrahma.portfolio.domain.model

data class ProjectData(
    var id: Int,
    var name: String,
    var logo: String,
    var role: String,
    var context: String,
    var tasks: List<String>,
    var categories : Map<Int, String>,
    var stacks: List<StackData>
)