package com.neobrahma.portfolio.data.mock

import com.neobrahma.portfolio.data.CheckoutPortfolioDataSource
import javax.inject.Inject

class CheckoutPortfolioDataSourceImpl @Inject constructor() : CheckoutPortfolioDataSource {

    override fun checkoutPortfolio(): PortfolioDAO {
        return portfolioDAO
    }
}