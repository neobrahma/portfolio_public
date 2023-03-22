package com.neobrahma.portfolio.data.database.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.neobrahma.portfolio.data.database.room.filter.FilterDao
import com.neobrahma.portfolio.data.database.room.portofolio.PortfolioDao
import com.neobrahma.portfolio.data.database.room.table.*
import com.neobrahma.portfolio.data.database.room.table.crossref.ProjectStackCrossRef

@Database(
    entities = [
        Stack::class,
        Company::class,
        Client::class,
        Category::class,
        Project::class,
        Task::class,
        ProjectStackCrossRef::class
    ], version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun portfolioDAO(): PortfolioDao

    abstract fun filterDAO(): FilterDao
}