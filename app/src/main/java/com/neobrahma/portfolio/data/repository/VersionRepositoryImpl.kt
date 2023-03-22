package com.neobrahma.portfolio.data.repository

import com.neobrahma.portfolio.data.VersionLocalDataSource
import com.neobrahma.portfolio.data.VersionRemoteDataSource
import com.neobrahma.portfolio.domain.repository.VersionRepository
import javax.inject.Inject

class VersionRepositoryImpl @Inject constructor(
    private val localDataSource: VersionLocalDataSource,
    private val remoteDataSource: VersionRemoteDataSource,
) : VersionRepository {

    override suspend fun getLocalVersion(): Int {
        return localDataSource.getVersion()
    }

    override suspend fun getRemoteVersion(): Int {
        return remoteDataSource.getVersion()
    }

    override suspend fun updateVersion() {
        localDataSource.updateVersion()
    }
}