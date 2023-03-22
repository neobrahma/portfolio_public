package com.neobrahma.portfolio.domain.repository

interface PortfolioRepository {
    suspend fun checkoutPortfolio()
}