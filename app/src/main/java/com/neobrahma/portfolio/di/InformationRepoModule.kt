package com.neobrahma.portfolio.di

import com.neobrahma.portfolio.data.InformationDataSource
import com.neobrahma.portfolio.data.database.InformationLocalDataSourceImpl
import com.neobrahma.portfolio.data.repository.InformationRepositoryImpl
import com.neobrahma.portfolio.domain.repository.InformationRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface InformationRepoModule {

    @Singleton
    @Binds
    fun bindInformationRepository(impl: InformationRepositoryImpl): InformationRepository

    @Singleton
    @Binds
    fun bindInformationDataSource(impl: InformationLocalDataSourceImpl): InformationDataSource
}