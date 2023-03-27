package com.neobrahma.portfolio.data

import com.neobrahma.portfolio.data.mock.PortfolioDAO

interface CheckoutPortfolioDataSource {

    fun checkoutPortfolio(): PortfolioDAO

}