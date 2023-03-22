package com.neobrahma.portfolio.domain.repository

interface VersionRepository {
    suspend fun getLocalVersion(): Int
    suspend fun getRemoteVersion(): Int
    suspend fun updateVersion()
}