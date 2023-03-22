package com.neobrahma.portfolio.presentation.tree.client

import com.neobrahma.portfolio.presentation.tree.model.Tree

sealed class ClientUiState {
    class DisplayTree(val tree: List<Tree.PrimaryItem>) : ClientUiState()
    object DisplayLoader : ClientUiState()
}