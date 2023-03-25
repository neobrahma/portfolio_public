package com.neobrahma.portfolio.data.database.room.portfolio

import androidx.room.Dao
import androidx.room.Query
import com.neobrahma.portfolio.data.database.room.table.*
import com.neobrahma.portfolio.data.database.room.table.crossref.ProjectStackCrossRef

@Dao
interface PortfolioTestDao {

    @Query("SELECT * FROM category")
    suspend fun getCategories(): List<Category>

    @Query("SELECT * FROM stack")
    suspend fun getStacks(): List<Stack>

    @Query("SELECT * FROM company")
    suspend fun getCompanies(): List<Company>

    @Query("SELECT * FROM project")
    suspend fun getProjects(): List<Project>

    @Query("SELECT * FROM task")
    suspend fun getTasks(): List<Task>

    @Query("SELECT * FROM ProjectStackCrossRef")
    suspend fun getProjectStackCrossRefs(): List<ProjectStackCrossRef>

    @Query("SELECT * FROM client")
    suspend fun getClient(): List<Client>

}