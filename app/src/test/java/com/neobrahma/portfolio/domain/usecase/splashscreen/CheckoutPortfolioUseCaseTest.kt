package com.neobrahma.portfolio.domain.usecase.splashscreen

import com.neobrahma.portfolio.domain.repository.SplashscreenRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class CheckoutPortfolioUseCaseTest {

    @Mock
    private lateinit var repository: SplashscreenRepository

    private lateinit var useCase: CheckoutPortfolioUseCase

    @Before
    fun setup() {
        useCase = CheckoutPortfolioUseCase(repository)
    }

    @Test
    fun whenInvokeUseCase_ifInternetTrueAndLocalVersionIs0_shouldCallCheckoutPortfolio() {
        runBlocking {
            val isInternet = true
            whenever(repository.getLocalVersion()).thenReturn(0)
            useCase.invoke(isInternet)
            verify(repository).checkoutPortfolio()
        }
    }

    @Test
    fun whenInvokeUseCase_ifInternetTrueAndLocalVersionIs0_shouldUpdateVersion() {
        runBlocking {
            val isInternet = true
            whenever(repository.getLocalVersion()).thenReturn(0)
            useCase.invoke(isInternet)
            verify(repository).updateVersion()
        }
    }

    @Test
    fun whenInvokeUseCase_ifInternetTrueAndLocalVersionIs0_returnContinue() {
        runBlocking {
            val isInternet = true
            val expected = CheckoutPortfolioState.Continue
            whenever(repository.getLocalVersion()).thenReturn(0)
            val actual = useCase.invoke(isInternet)
            Assert.assertEquals(expected, actual)
        }
    }

    @Test
    fun whenInvokeUseCase_ifInternetFalseAndLocalVersionIs0_returnError() {
        runBlocking {
            val isInternet = false
            val expected = CheckoutPortfolioState.Error
            whenever(repository.getLocalVersion()).thenReturn(0)
            val actual = useCase.invoke(isInternet)
            Assert.assertEquals(expected, actual)
        }
    }

    @Test
    fun whenInvokeUseCase_ifInternetTrueAndLocalVersionMoreThan0_shouldGetRemoteVersion() {
        runBlocking {
            whenever(repository.getLocalVersion()).thenReturn(1)
            whenever(repository.getRemoteVersion()).thenReturn(1)
            val isInternet = true
            useCase.invoke(isInternet)
            verify(repository).getRemoteVersion()
        }
    }

    @Test
    fun whenInvokeUseCase_ifInternetTrueAndLocalVersionMoreThan0_returnContinue() {
        runBlocking {
            val expected = CheckoutPortfolioState.Continue
            whenever(repository.getLocalVersion()).thenReturn(1)
            whenever(repository.getRemoteVersion()).thenReturn(1)
            val isInternet = true
            val actual = useCase.invoke(isInternet)
            Assert.assertEquals(expected, actual)
        }
    }

    @Test
    fun whenInvokeUseCase_ifInternetFalseAndLocalVersionMoreThan0_returnContinue() {
        runBlocking {
            val expected = CheckoutPortfolioState.Continue
            whenever(repository.getLocalVersion()).thenReturn(1)
            val isInternet = false
            val actual = useCase.invoke(isInternet)
            Assert.assertEquals(expected, actual)
        }
    }

}