package com.neobrahma.portfolio.presentation.information

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.neobrahma.portfolio.ui.theme.PortfolioTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InformationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PortfolioTheme {
                InformationApp()
            }
        }
    }
}

