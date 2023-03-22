package com.neobrahma.portfolio.presentation.tree.mapper

import com.neobrahma.portfolio.domain.model.StackData
import com.neobrahma.portfolio.presentation.Utils
import com.neobrahma.portfolio.presentation.tree.TreeMapper
import com.neobrahma.portfolio.ui.component.item.stack.StackItemUI
import com.neobrahma.portfolio.ui.component.stack.StackUI

abstract class TreeProjectMapper(
    utils: Utils
) : TreeMapper(utils) {

    companion object {
        private const val SIZE_MAX = 3
    }

    protected fun mapStack(
        categories: Map<Int, String>,
        stacks: List<StackData>
    ): List<List<StackItemUI>> {
        val mapSpace: MutableMap<Int, Int> = mutableMapOf()
        val stackItemsUI: MutableList<MutableList<StackItemUI>> = mutableListOf()
        var sizeColumn = -1
        categories.forEach { category ->
            val projectsByCategory = stacks.filter { it.categoryId == category.key }
            val stack = StackItemUI(
                title = category.value,
                stacks = projectsByCategory.map {
                    StackUI(
                        id = it.stackId,
                        label = it.label,
                        idDrawable = utils.drawableFromResourcesByName(it.iconId),
                    )
                }
            )

            val quantity = projectsByCategory.size
            var addAtColumn = 0
            if (quantity >= SIZE_MAX) {
                sizeColumn++
                addAtColumn = sizeColumn
            } else {
                if (mapSpace.isEmpty()) {
                    sizeColumn++
                    mapSpace[sizeColumn] = SIZE_MAX - quantity
                    addAtColumn = sizeColumn
                } else {
                    val result = mapSpace.filter { it.value != 0 && quantity <= it.value }
                    if (result.isNotEmpty()) {
                        result.firstNotNullOf {
                            mapSpace[it.key] = it.value - quantity
                            addAtColumn = it.key
                        }
                        if (mapSpace[addAtColumn] == 0) {
                            mapSpace.remove(addAtColumn)
                        }
                    } else {
                        sizeColumn++
                        mapSpace[sizeColumn] = SIZE_MAX - quantity
                        addAtColumn = sizeColumn
                    }
                }
            }

            if (stackItemsUI.size == addAtColumn) {
                stackItemsUI.add(mutableListOf())
            }

            stackItemsUI[addAtColumn].add(stack)
        }
        return stackItemsUI
    }
}