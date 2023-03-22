package com.neobrahma.portfolio.domain.usecase.splashscreen

import android.util.Log
import com.neobrahma.portfolio.domain.repository.SplashscreenRepository
import javax.inject.Inject

class CheckoutPortfolioUseCase @Inject constructor(
    private val splashscreenRepository: SplashscreenRepository
) {

    suspend operator fun invoke(): CheckoutPortfolioState {
        val localVersion = splashscreenRepository.getLocalVersion()
        if (localVersion == 0) {
            return if (isInternet()) {
                splashscreenRepository.checkoutPortfolio()
                splashscreenRepository.updateVersion()
                CheckoutPortfolioState.Continue
            } else {
                CheckoutPortfolioState.Error
            }
        } else {
            if (isInternet()) {
                val remoteVersion = splashscreenRepository.getRemoteVersion()
                if (remoteVersion > localVersion) {
                    //update DB
                    Log.e("tom971", "invoke: update")
                }
            }
            return CheckoutPortfolioState.Continue
        }
    }

    private fun isInternet(): Boolean {
        return true
    }

}

sealed class CheckoutPortfolioState {
    object Continue : CheckoutPortfolioState()
    object Error : CheckoutPortfolioState()
}