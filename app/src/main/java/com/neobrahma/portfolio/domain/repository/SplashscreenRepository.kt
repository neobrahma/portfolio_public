package com.neobrahma.portfolio.domain.repository

interface SplashscreenRepository {
    suspend fun checkoutPortfolio()
    suspend fun getLocalVersion(): Int
    suspend fun getRemoteVersion(): Int
    suspend fun updateVersion()
}