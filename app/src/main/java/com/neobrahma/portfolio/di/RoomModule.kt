package com.neobrahma.portfolio.di

import android.content.Context
import androidx.room.Room
import com.neobrahma.portfolio.data.database.room.AppDatabase
import com.neobrahma.portfolio.data.database.room.filter.FilterDao
import com.neobrahma.portfolio.data.database.room.information.InformationDao
import com.neobrahma.portfolio.data.database.room.portofolio.PortfolioDao
import com.neobrahma.portfolio.data.database.room.tree.TreeDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    @Singleton
    @Provides
    fun providePortfolioDAO(appDatabase: AppDatabase): PortfolioDao {
        return appDatabase.portfolioDAO()
    }

    @Singleton
    @Provides
    fun provideFilterDAO(appDatabase: AppDatabase): FilterDao {
        return appDatabase.filterDAO()
    }

    @Singleton
    @Provides
    fun provideInformationDAO(appDatabase: AppDatabase): InformationDao {
        return appDatabase.informationDao()
    }

    @Singleton
    @Provides
    fun provideTreeDAO(appDatabase: AppDatabase): TreeDao {
        return appDatabase.treeDAO()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "Portfolio"
        ).build()
    }
}