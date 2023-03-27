package com.neobrahma.portfolio.data.database.room.information

import androidx.room.Dao
import androidx.room.Query
import com.neobrahma.portfolio.data.database.room.table.Category

@Dao
interface InformationDao {

    @Query("SELECT COUNT(*) FROM project")
    suspend fun countProjects(): Int

    @Query("SELECT * FROM category")
    suspend fun getCategories(): List<Category>

    @Query(
        "SELECT stack.label AS label, COUNT(result.projectId) AS count FROM\n" +
                "(SELECT projectstackcrossref.* FROM projectstackcrossref, stack WHERE stack.categoryId = :categoryId AND stack.stackId = projectstackcrossref.stackId) AS result,\n" +
                "stack \n" +
                " WHERE \n" +
                " result.stackId = stack.stackId\n" +
                " GROUP BY result.stackId"
    )
    suspend fun getStaticalByCategory(categoryId: Int): List<StackLabelWithCount>
}