package com.neobrahma.portfolio.presentation.tree

import com.neobrahma.portfolio.domain.model.ClientData
import com.neobrahma.portfolio.domain.model.CompanyData
import com.neobrahma.portfolio.domain.model.ProjectData
import com.neobrahma.portfolio.presentation.Utils
import com.neobrahma.portfolio.presentation.tree.model.Tree
import com.neobrahma.portfolio.ui.R
import com.neobrahma.portfolio.ui.component.dot.DotStateView
import com.neobrahma.portfolio.ui.component.item.primary.PrimaryItemUI

abstract class TreeMapper(
    protected val utils: Utils
) {

    protected fun mapperCompanyToUI(
        data: CompanyData,
        isClosable: Boolean = true,
        isOpenable: Boolean = false

    ): PrimaryItemUI {
        return PrimaryItemUI(
            title = data.name,
            header = data.date,
            footer = data.city,
            idDrawable = utils.drawableFromResourcesByName(data.logo),
            isClosable = isClosable,
            isOpenable = isOpenable,
            color = utils.getColor(R.color.purple)
        )
    }

    protected fun mapperProjectToUI(
        data: ProjectData,
        isClosable: Boolean = true,
        isOpenable: Boolean = false
    ): PrimaryItemUI {
        return PrimaryItemUI(
            title = data.name,
            header = data.role,
            footer = "X month",
            idDrawable = utils.drawableFromResourcesByName(data.logo),
            isClosable = isClosable,
            isOpenable = isOpenable,
            color = utils.getColor(R.color.orange)
        )
    }

    protected fun mapperClientToUI(
        data: ClientData,
        isClosable: Boolean = true,
        isOpenable: Boolean = false
    ): PrimaryItemUI {
        return PrimaryItemUI(
            title = data.name,
            header = data.date,
            footer = "X month",
            idDrawable = utils.drawableFromResourcesByName(data.logo),
            isClosable = isClosable,
            isOpenable = isOpenable,
            color = utils.getColor(R.color.blue_light)
        )
    }

    protected fun mapperProjectToDotUi(isGradient: Boolean, isClients: Boolean): List<Int> {
        return if (isGradient && isClients) {
            listOf(
                utils.getColor(R.color.blue_light),
                utils.getColor(R.color.orange)
            )
        } else if (isGradient) {
            listOf(
                utils.getColor(R.color.purple),
                utils.getColor(R.color.orange)
            )
        } else {
            listOf(utils.getColor(R.color.orange))
        }
    }

    protected fun initStateDot(list: List<Tree>) {
        if (list.size == 1) {
            list.first().dotUI.stateDote = DotStateView.POINT
        } else {
            list.first().dotUI.stateDote = DotStateView.START
            list.last().dotUI.stateDote = DotStateView.END
        }
    }
}