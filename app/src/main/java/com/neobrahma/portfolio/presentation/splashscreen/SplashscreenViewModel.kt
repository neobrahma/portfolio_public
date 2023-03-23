package com.neobrahma.portfolio.presentation.splashscreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neobrahma.portfolio.domain.usecase.splashscreen.CheckoutPortfolioState
import com.neobrahma.portfolio.domain.usecase.splashscreen.CheckoutPortfolioUseCase
import com.neobrahma.portfolio.presentation.ConnectivityUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashscreenViewModel @Inject constructor(
    private val checkoutPortfolioUseCase: CheckoutPortfolioUseCase,
    private val connectivityUtils: ConnectivityUtils
) : ViewModel() {

    private val _liveDataSplashScreen = MutableLiveData<PortfolioAction>()
    val liveDataSplashScreen: LiveData<PortfolioAction>
        get() = _liveDataSplashScreen

    fun startPortfolio() {
        viewModelScope.launch(Dispatchers.IO) {
            when (checkoutPortfolioUseCase(connectivityUtils.isInternet())) {
                is CheckoutPortfolioState.Continue -> {
                    _liveDataSplashScreen.postValue(PortfolioAction.Continue)
                }
                is CheckoutPortfolioState.Error -> {
                    _liveDataSplashScreen.postValue(PortfolioAction.Error)
                }
            }
        }
    }
}

sealed class PortfolioAction {
    object Continue : PortfolioAction()
    object Error : PortfolioAction()
}