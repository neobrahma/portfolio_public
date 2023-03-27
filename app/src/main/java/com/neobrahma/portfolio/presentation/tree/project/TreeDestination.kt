package com.neobrahma.portfolio.presentation.tree.project

interface TreeDestination {
    val destination: String
}

object CompaniesDestination : TreeDestination {
    override val destination: String
        get() = "companies"
}

object CompanyDestination : TreeDestination {
    override val destination: String
        get() = "company"
}

object ClientDestination : TreeDestination {
    override val destination: String
        get() = "company/client"
}

object ProjectDestination : TreeDestination {
    override val destination: String
        get() = "company/project"
}

object ProjectClientDestination : TreeDestination {
    override val destination: String
        get() = "company/client/project"
}