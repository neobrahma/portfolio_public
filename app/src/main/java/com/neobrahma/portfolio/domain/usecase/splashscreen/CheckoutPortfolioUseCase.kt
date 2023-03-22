package com.neobrahma.portfolio.domain.usecase.splashscreen

import android.util.Log
import com.neobrahma.portfolio.domain.repository.PortfolioRepository
import com.neobrahma.portfolio.domain.repository.VersionRepository
import javax.inject.Inject

class CheckoutPortfolioUseCase @Inject constructor(
    private val versionRepo: VersionRepository,
    private val portfolioRepository: PortfolioRepository
) {

    suspend operator fun invoke(): CheckoutPortfolioState {
        val localVersion = versionRepo.getLocalVersion()
        if (localVersion == 0) {
            return if (isInternet()) {
                portfolioRepository.checkoutPortfolio()
                versionRepo.updateVersion()
                CheckoutPortfolioState.Continue
            } else {
                CheckoutPortfolioState.Error
            }
        } else {
            if (isInternet()) {
                val remoteVersion = versionRepo.getRemoteVersion()
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