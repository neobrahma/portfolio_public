package com.neobrahma.portfolio.presentation.tree.companies

import com.neobrahma.portfolio.domain.model.CompanyData
import com.neobrahma.portfolio.presentation.Utils
import com.neobrahma.portfolio.presentation.tree.TreeMapper
import com.neobrahma.portfolio.presentation.tree.model.Action
import com.neobrahma.portfolio.presentation.tree.model.Tree
import com.neobrahma.portfolio.presentation.tree.project.CompanyDestination
import com.neobrahma.portfolio.ui.R
import com.neobrahma.portfolio.ui.component.dot.DotUI
import javax.inject.Inject

class CompaniesMapper @Inject constructor(
    utils: Utils
) : TreeMapper(utils) {
    fun mapperToUI(companies: List<CompanyData>): List<Tree.PrimaryItem> {
        val companiesUI: MutableList<Tree.PrimaryItem> = mutableListOf()

        companies.forEach { data ->

            val route = CompanyDestination.destination

            companiesUI.add(
                Tree.PrimaryItem(
                    DotUI(colors = listOf(utils.getColor(R.color.purple))),
                    mapperCompanyToUI(data, isClosable = false, isOpenable = true),
                    Action.Navigate(route, listOf(data.companyId))
                )
            )
        }

        initStateDot(companiesUI)

        return companiesUI
    }


}