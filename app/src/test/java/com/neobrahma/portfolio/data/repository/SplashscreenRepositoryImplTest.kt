package com.neobrahma.portfolio.data.repository

import com.neobrahma.portfolio.data.CheckoutPortfolioDataSource
import com.neobrahma.portfolio.data.SavePortfolioDataSource
import com.neobrahma.portfolio.data.VersionLocalDataSource
import com.neobrahma.portfolio.data.VersionRemoteDataSource
import com.neobrahma.portfolio.domain.repository.SplashscreenRepository
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class SplashscreenRepositoryImplTest {
    @Mock
    private lateinit var checkoutPortfolioDataSource: CheckoutPortfolioDataSource

    @Mock
    private lateinit var savePortfolioDataSource: SavePortfolioDataSource

    @Mock
    private lateinit var versionLocalDataSource: VersionLocalDataSource

    @Mock
    private lateinit var versionRemoteDataSource: VersionRemoteDataSource

    private lateinit var repository: SplashscreenRepository

    @Before
    fun setup() {
        repository = SplashscreenRepositoryImpl(
            checkoutPortfolioDataSource,
            savePortfolioDataSource,
            versionLocalDataSource,
            versionRemoteDataSource
        )
    }

    @Test
    fun whenCheckoutPortfolio_DSShouldCallCheckoutPortfolio() {
        runBlocking {
            whenever(checkoutPortfolioDataSource.checkoutPortfolio()).thenReturn(portfolioDAO)
            repository.checkoutPortfolio()
            verify(checkoutPortfolioDataSource).checkoutPortfolio()
        }
    }

    @Test
    fun whenCheckoutPortfolio_DSShouldCallAddAllStack() {
        runBlocking {
            whenever(checkoutPortfolioDataSource.checkoutPortfolio()).thenReturn(portfolioDAO)
            repository.checkoutPortfolio()
            verify(savePortfolioDataSource).addAllStack(portfolioDAO.stacks)
        }
    }

    @Test
    fun whenCheckoutPortfolio_DSShouldCallAddAllCategory() {
        runBlocking {
            whenever(checkoutPortfolioDataSource.checkoutPortfolio()).thenReturn(portfolioDAO)
            repository.checkoutPortfolio()
            verify(savePortfolioDataSource).addAllCategory(portfolioDAO.categories)
        }
    }

    @Test
    fun whenCheckoutPortfolio_DSShouldCallAllCompany() {
        runBlocking {
            whenever(checkoutPortfolioDataSource.checkoutPortfolio()).thenReturn(portfolioDAO)
            repository.checkoutPortfolio()
            verify(savePortfolioDataSource).addAllCategory(portfolioDAO.categories)
        }
    }

    @Test
    fun whenGetLocalVersion_DSShouldGetLocalVersion() {
        runBlocking {
            whenever(versionLocalDataSource.getVersion()).thenReturn(1)
            repository.getLocalVersion()
            verify(versionLocalDataSource).getVersion()
        }
    }

    @Test
    fun whenGetRemoteVersion_DSShouldGetRemoteVersion() {
        runBlocking {
            whenever(versionRemoteDataSource.getVersion()).thenReturn(1)
            repository.getRemoteVersion()
            verify(versionRemoteDataSource).getVersion()
        }
    }

    @Test
    fun whenUpdateVersion_DSShouldUpdateVersion() {
        runBlocking {
            repository.updateVersion()
            verify(versionLocalDataSource).updateVersion()
        }
    }
}