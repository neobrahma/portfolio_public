package com.neobrahma.portfolio.data.rest

import com.neobrahma.portfolio.data.VersionRemoteDataSource
import javax.inject.Inject

class VersionRemoteDataSourceImpl @Inject constructor() : VersionRemoteDataSource {
    override suspend fun getVersion(): Int {
        return 1
    }
}