package com.neobrahma.portfolio.presentation.tree.project

import com.neobrahma.portfolio.presentation.tree.model.Tree

sealed class ProjectUiState {
    class DisplayTree(val tree: List<Tree>) : ProjectUiState()
    object DisplayLoader : ProjectUiState()
}