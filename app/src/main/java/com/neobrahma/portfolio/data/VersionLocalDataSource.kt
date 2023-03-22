package com.neobrahma.portfolio.data

interface VersionLocalDataSource {
    suspend fun getVersion(): Int
    suspend fun updateVersion()
}