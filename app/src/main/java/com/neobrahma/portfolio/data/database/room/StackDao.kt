package com.neobrahma.portfolio.data.database.room//package com.neobrahma.portfolio.data.database
//
//import androidx.room.*
//import com.neobrahma.portfolio.data.database.table.*
//
//@Dao
//interface StackDao {
//    @Query("SELECT * FROM stack")
//    suspend fun getAllStack(): List<Stack>
//
//    @Insert
//    suspend fun insertCompany(company: Company)
//
//    @Insert
//    suspend fun insertAllStack(users: List<Stack>)
//
//    @Insert
//    suspend fun insertProject(project: Project)
//
//    @Insert
//    suspend fun insertTask(task: Task)
//
//    @Insert
//    suspend fun insertProjectStackCrossRef(value: ProjectStackCrossRef)
//
//    @Insert
//    suspend fun insertAllCategory(users: List<Category>)
//
//    @Transaction
//    @Query("SELECT * FROM project")
//    suspend fun getCompanies(): List<ProjectWithStacksAndTasks>
//
//}
//
//data class CompanyProjects(
//    @Embedded val user: Company,
//    @Relation(
//        parentColumn = "idCompany",
//        entityColumn = "idCompany"
//    )
//    val projects: List<Project>
//)
//
//data class ProjectWithStacksAndTasks(
//    @Embedded val project: Project,
//    @Relation(
//        parentColumn = "idProject",
//        entityColumn = "idProject",
//        entity = Task::class,
//        projection = ["task"]
//    )
//    val tasks: List<String>,
//    @Relation(
//        parentColumn = "idProject",
//        entityColumn = "id",
//        associateBy = Junction(ProjectStackCrossRef::class)
//    )
//    val stacks: List<Stack>
//
//)