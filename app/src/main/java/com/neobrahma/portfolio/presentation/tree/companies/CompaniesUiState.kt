package com.neobrahma.portfolio.presentation.tree.companies

import com.neobrahma.portfolio.presentation.tree.model.Tree

sealed class CompaniesUiState {
    class DisplayTree(val tree: List<Tree.PrimaryItem>) : CompaniesUiState()
    object DisplayLoader : CompaniesUiState()
}