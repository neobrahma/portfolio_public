package com.neobrahma.portfolio.presentation.tree.client

import com.neobrahma.portfolio.domain.model.ClientData
import com.neobrahma.portfolio.domain.model.CompanyData
import com.neobrahma.portfolio.presentation.Utils
import com.neobrahma.portfolio.presentation.tree.TreeMapper
import com.neobrahma.portfolio.presentation.tree.model.Action
import com.neobrahma.portfolio.presentation.tree.model.Tree
import com.neobrahma.portfolio.presentation.tree.project.CompaniesDestination
import com.neobrahma.portfolio.presentation.tree.project.ProjectClientDestination
import com.neobrahma.portfolio.ui.R
import com.neobrahma.portfolio.ui.component.dot.DotUI
import javax.inject.Inject

class ClientMapper @Inject constructor(
    utils: Utils
) : TreeMapper(utils) {
    fun mapperToUi(company: CompanyData, clientData: ClientData): List<Tree.PrimaryItem> {
        val itemsUI: MutableList<Tree.PrimaryItem> = mutableListOf()

        itemsUI.add(
            Tree.PrimaryItem(
                DotUI(colors = listOf(utils.getColor(R.color.purple))),
                mapperCompanyToUI(company),
                Action.PopUpTo(CompaniesDestination.destination)
            )
        )

        itemsUI.add(
            Tree.PrimaryItem(
                DotUI(
                    colors = listOf(
                        utils.getColor(R.color.purple),
                        utils.getColor(R.color.blue_light)
                    )
                ),
                mapperClientToUI(clientData),
                Action.PopBackStack
            )
        )

        clientData.projects.forEachIndexed { index, data ->
            itemsUI.add(
                Tree.PrimaryItem(
                    DotUI(colors = mapperProjectToDotUi(index == 0, company.clients.isNotEmpty())),
                    mapperProjectToUI(data, isClosable = false, isOpenable = true),
                    Action.Navigate(
                        route = ProjectClientDestination.destination,
                        ids = listOf(company.companyId, clientData.clientId, data.id),
                    )
                )
            )
        }

        initStateDot(itemsUI)

        return itemsUI
    }
}