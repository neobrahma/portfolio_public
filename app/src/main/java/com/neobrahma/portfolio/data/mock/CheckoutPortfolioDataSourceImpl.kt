package com.neobrahma.portfolio.data.mock

import com.neobrahma.portfolio.data.CheckoutPortfolioDataSource
import com.neobrahma.portfolio.data.TreeDataSource
import com.neobrahma.portfolio.data.mock.model.*
import com.neobrahma.portfolio.domain.model.*
import javax.inject.Inject

class CheckoutPortfolioDataSourceImpl @Inject constructor() : CheckoutPortfolioDataSource {

    override fun checkoutPortfolio() : PortfolioDAO{
        return portfolioDAO
    }
}