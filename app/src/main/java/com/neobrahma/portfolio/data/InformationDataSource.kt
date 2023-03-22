package com.neobrahma.portfolio.data

import com.neobrahma.portfolio.domain.model.InformationData

interface InformationDataSource {

    suspend fun getStackStatistical(): InformationData

}