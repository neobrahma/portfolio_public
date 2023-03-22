package com.neobrahma.portfolio.presentation.tree.model

sealed interface Action {
    class Navigate(val route: String, val ids : List<Int>) : Action
    class NavigateFromCompany(val route: String, val ids : List<Int>, val isClient : Boolean = false) : Action
    class PopUpTo(val route : String) : Action
    object PopBackStack : Action
}