package com.neobrahma.portfolio.data.database.room.tree

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.neobrahma.portfolio.data.database.room.table.*
import com.neobrahma.portfolio.data.database.room.table.crossref.ProjectStackCrossRef
import kotlinx.coroutines.flow.Flow

@Dao
interface TreeDao {

    @Query("SELECT * FROM company ORDER BY companyId DESC")
    suspend fun getCompanies(): List<Company>

    @Query("SELECT * FROM company WHERE companyId = :companyId")
    suspend fun getCompany(companyId: Int): Company

    @Query("SELECT * FROM client WHERE companyId = :companyId")
    suspend fun getClients(companyId: Int): List<Client>

    @Query("SELECT * FROM client WHERE clientId = :clientId")
    suspend fun getClient(clientId: Int): Client

    @Transaction
    @Query("SELECT * FROM project WHERE projectId = :projectId")
    suspend fun getProject(projectId: Int): ProjectWithStacksAndTasks

    @Transaction
    @Query("SELECT * FROM project WHERE ownerId = :ownerId")
    suspend fun getProjectByOwner(ownerId: Int): List<ProjectWithStacksAndTasks>

    @Query("SELECT stackId FROM stack WHERE isFiltered = 1")
    fun getSelectedStacks(): Flow<List<Int>>

    @Query("SELECT DISTINCT projectId FROM ProjectStackCrossRef WHERE stackId IN (:stacks)")
    suspend fun getProjectFiltered(stacks : List<Int>): List<Int>

    @Query("SELECT * FROM Category WHERE categoryId IN (:categories)")
    suspend fun getCategories(categories : Set<Int>): List<Category>
}