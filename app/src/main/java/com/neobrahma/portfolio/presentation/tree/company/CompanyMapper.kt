package com.neobrahma.portfolio.presentation.tree.company

import com.neobrahma.portfolio.domain.model.CompanyData
import com.neobrahma.portfolio.presentation.Utils
import com.neobrahma.portfolio.presentation.tree.TreeMapper
import com.neobrahma.portfolio.presentation.tree.model.Action
import com.neobrahma.portfolio.presentation.tree.model.Tree
import com.neobrahma.portfolio.ui.R
import com.neobrahma.portfolio.ui.component.dot.DotUI
import javax.inject.Inject

class CompanyMapper @Inject constructor(
    utils: Utils
) : TreeMapper(utils) {
    fun mapperToUi(company: CompanyData): List<Tree.PrimaryItem> {
        val itemsUI: MutableList<Tree.PrimaryItem> = mutableListOf()

        itemsUI.add(
            Tree.PrimaryItem(
                DotUI(colors = listOf(utils.getColor(R.color.purple))),
                mapperCompanyToUI(company),
                Action.PopBackStack
            )

        )

        company.clients.forEachIndexed { index, data ->
            itemsUI.add(
                Tree.PrimaryItem(
                    DotUI(colors = mapperClientToDotUi(index == 0)),
                    mapperClientToUI(data, isClosable = false, isOpenable = true),
                    Action.Navigate(
                        route = "company/${company.companyId}/client/${data.clientId}"
                    )
                )
            )
        }

        company.projects.forEachIndexed { index, data ->
            itemsUI.add(
                Tree.PrimaryItem(
                    DotUI(colors = mapperProjectToDotUi(index == 0, company.clients.isNotEmpty())),
                    mapperProjectToUI(data, isClosable = false, isOpenable = true),
                    Action.Navigate(
                        route = "company/${company.companyId}/project/${data.id}"
                    )
                )
            )
        }

        initStateDot(itemsUI)

        return itemsUI
    }

    private fun mapperClientToDotUi(isGradient: Boolean): List<Int> {
        return if (isGradient) {
            listOf(
                utils.getColor(R.color.purple),
                utils.getColor(R.color.blue_light)
            )
        } else {
            listOf(utils.getColor(R.color.blue_light))
        }
    }
}