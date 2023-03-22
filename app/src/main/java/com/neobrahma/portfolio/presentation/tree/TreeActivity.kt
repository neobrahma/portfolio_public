package com.neobrahma.portfolio.presentation.tree

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import com.neobrahma.portfolio.ui.theme.PortfolioTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TreeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PortfolioTheme {
                TreeApp()
            }
        }
    }
}