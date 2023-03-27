package com.neobrahma.portfolio.presentation.splashscreen

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.neobrahma.portfolio.presentation.tree.TreeActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashscreenActivity : AppCompatActivity() {

    private val viewModel: SplashscreenViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.liveDataSplashScreen.observe(this, ::observerSplashScreen)
        viewModel.startPortfolio()
    }

    private fun observerSplashScreen(view: PortfolioAction) {
        when (view) {
            is PortfolioAction.Continue -> {
                onContinue()
            }
            is PortfolioAction.Error -> {
                //creer screen compose pr afficher popup
                Log.e("tom971", "observerSplashScreen: display popup erreur internet")
            }
        }
    }

    private fun onContinue() {
        val intent = Intent(this@SplashscreenActivity, TreeActivity::class.java)
        startActivity(intent)
        finish()
    }
}