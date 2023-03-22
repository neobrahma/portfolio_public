package com.neobrahma.portfolio.presentation.tree.project

import com.neobrahma.portfolio.domain.model.ClientData
import com.neobrahma.portfolio.domain.model.CompanyData
import com.neobrahma.portfolio.domain.model.ProjectData
import com.neobrahma.portfolio.presentation.Utils
import com.neobrahma.portfolio.presentation.tree.mapper.TreeProjectMapper
import com.neobrahma.portfolio.presentation.tree.model.Action
import com.neobrahma.portfolio.presentation.tree.model.Tree
import com.neobrahma.portfolio.ui.R
import com.neobrahma.portfolio.ui.component.dot.DotUI
import com.neobrahma.portfolio.ui.component.item.context.ContextItemUI
import com.neobrahma.portfolio.ui.component.item.task.TaskItemUI
import javax.inject.Inject

class ProjectClientMapper @Inject constructor(
    utils: Utils
) : TreeProjectMapper(utils) {

    fun mapperProjectToUI(
        companyData: CompanyData,
        clientData: ClientData,
        projectData: ProjectData
    ): List<Tree> {
        val itemsUI: MutableList<Tree> = mutableListOf()

        itemsUI.add(
            Tree.PrimaryItem(
                DotUI(colors = listOf(utils.getColor(R.color.purple))),
                mapperCompanyToUI(companyData),
                Action.PopUpTo("home")
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
                Action.PopUpToFrom(
                    "company/${companyData.companyId}",
                    "home",
                    false
                )
            )
        )

        itemsUI.add(
            Tree.PrimaryItem(
                DotUI(
                    colors = listOf(
                        utils.getColor(R.color.blue_light),
                        utils.getColor(R.color.orange)
                    )
                ),
                mapperProjectToUI(projectData),
                Action.PopBackStack
            )
        )

        itemsUI.add(
            Tree.ContextItem(
                dotUI = DotUI(
                    colors = listOf(
                        utils.getColor(R.color.orange),
                        utils.getColor(R.color.red)
                    )
                ),
                ContextItemUI(
                    title = "Context",
                    description = projectData.context
                )
            )
        )
        itemsUI.add(
            Tree.TaskItem(
                dotUI = DotUI(
                    colors = listOf(utils.getColor(R.color.red))
                ),
                TaskItemUI(
                    title = "Tasks",
                    description = projectData.tasks
                )
            )
        )

        itemsUI.add(
            Tree.StackItem(
                dotUI = DotUI(
                    colors = listOf(utils.getColor(R.color.red))
                ),
                mapStack(projectData.categories, projectData.stacks)
            )
        )

        initStateDot(itemsUI)

        return itemsUI
    }
}