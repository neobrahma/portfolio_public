package com.neobrahma.portfolio.presentation.tree.company

import com.neobrahma.portfolio.presentation.tree.model.Tree

sealed class CompanyUiState {
    class DisplayTree(val tree: List<Tree.PrimaryItem>) : CompanyUiState()
    object DisplayLoader : CompanyUiState()
}