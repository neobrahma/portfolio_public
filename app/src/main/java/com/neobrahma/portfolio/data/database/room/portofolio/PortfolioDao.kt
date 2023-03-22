package com.neobrahma.portfolio.data.database.room.portofolio

import androidx.room.Dao
import androidx.room.Insert
import com.neobrahma.portfolio.data.database.room.table.*
import com.neobrahma.portfolio.data.database.room.table.crossref.ProjectStackCrossRef

@Dao
interface PortfolioDao {

    @Insert
    suspend fun insertAllStack(users: List<Stack>)

    @Insert
    suspend fun insertAllCategory(users: List<Category>)

    @Insert
    suspend fun insertCompany(company: Company)

    @Insert
    suspend fun insertProject(project: Project)

    @Insert
    suspend fun insertClient(client: Client)

    @Insert
    suspend fun insertTask(task: Task)

    @Insert
    suspend fun insertProjectStackCrossRef(value: ProjectStackCrossRef)

}