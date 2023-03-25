package com.neobrahma.portfolio.data.database

import android.content.Context
import androidx.room.Room
import com.neobrahma.portfolio.data.database.room.AppDatabaseTest
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object TestRoomModule {

    @Provides
    @Named("test_db")
    fun provideInMemoryDb(@ApplicationContext context: Context) =
        Room.inMemoryDatabaseBuilder(
            context, AppDatabaseTest::class.java
        ).allowMainThreadQueries()
            .build()
}