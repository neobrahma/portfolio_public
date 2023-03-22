package com.neobrahma.portfolio.presentation.tree.model

sealed interface Action {
    class Navigate(val route: String) : Action
    class PopUpTo(val route : String) : Action
    object PopBackStack : Action
    class PopUpToFrom(val routeTo : String,
                   val routeFrom : String,
                   val inclusive : Boolean) : Action
}