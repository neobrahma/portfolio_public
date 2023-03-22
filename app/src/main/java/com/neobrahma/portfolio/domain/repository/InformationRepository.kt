package com.neobrahma.portfolio.domain.repository

import com.neobrahma.portfolio.domain.model.InformationData

interface InformationRepository {

    suspend fun getStackStatistical(): InformationData

}