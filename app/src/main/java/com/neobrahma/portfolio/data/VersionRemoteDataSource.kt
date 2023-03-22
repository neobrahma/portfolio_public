package com.neobrahma.portfolio.data

interface VersionRemoteDataSource {
    suspend fun getVersion(): Int
}