package com.neobrahma.portfolio.presentation.tree.companies

import android.content.Intent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.neobrahma.portfolio.R
import com.neobrahma.portfolio.presentation.filter.FilterActivity
import com.neobrahma.portfolio.presentation.information.InformationActivity
import com.neobrahma.portfolio.presentation.tree.HomeViewModel
import com.neobrahma.portfolio.presentation.tree.TreeView

@Composable
fun CompaniesScreen(navController: NavController, viewModel: HomeViewModel, modifier: Modifier) {
    Scaffold(
        bottomBar = {
            BottomAppBar(
                content = {
                    BottomBar()
                }
            )
        },
        content = {
            CompaniesContent(
                modifier = modifier
                    .padding(it),
                navController = navController,
                viewModel
            )
        }
    )
}

@Composable
fun BottomBar() {
    val context = LocalContext.current
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        ItemBottomBar(
            Modifier
                .fillMaxWidth()
                .weight(1f),
            R.drawable.ic_contact_phone,
            "Info"
        ) {
            context.startActivity(Intent(context, InformationActivity::class.java))
        }
        ItemBottomBar(
            Modifier
                .fillMaxWidth()
                .weight(1f),
            R.drawable.ic_filter_list,
            "Filter"
        ) {
            context.startActivity(Intent(context, FilterActivity::class.java))
        }
    }
}

@Composable
fun ItemBottomBar(
    modifier: Modifier, idDrawable: Int, text: String, listener: () -> Unit,
) {
    IconButton(
        onClick = listener,
        modifier = modifier
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painterResource(id = idDrawable),
                contentDescription = null,
                modifier = Modifier.size(24.dp),
            )
            Text(
                text = text,
                color = colorResource(id = R.color.text)
            )
        }
    }
}

@Composable
fun CompaniesContent(
    modifier: Modifier,
    navController: NavController,
    viewModel: HomeViewModel
) {

    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.initHomeScreen()
    }
    TreeView(modifier, navController, uiState)

}