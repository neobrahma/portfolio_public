package com.neobrahma.portfolio.presentation.tree.model

import com.neobrahma.portfolio.ui.component.dot.DotUI
import com.neobrahma.portfolio.ui.component.item.context.ContextItemUI
import com.neobrahma.portfolio.ui.component.item.primary.PrimaryItemUI
import com.neobrahma.portfolio.ui.component.item.stack.StackItemUI
import com.neobrahma.portfolio.ui.component.item.task.TaskItemUI

sealed class Tree(
    val dotUI: DotUI
) {
    class PrimaryItem(
        dotUI: DotUI,
        val primaryItemUI: PrimaryItemUI,
        val action: Action
    ) : Tree(dotUI)

    class ContextItem(
        dotUI: DotUI,
        val content : ContextItemUI
    ) : Tree(dotUI)

    class TaskItem(
        dotUI: DotUI,
        val content : TaskItemUI
    ) : Tree(dotUI)

    class StackItem(
        dotUI: DotUI,
        val content : List<List<StackItemUI>>
    ) : Tree(dotUI)
}