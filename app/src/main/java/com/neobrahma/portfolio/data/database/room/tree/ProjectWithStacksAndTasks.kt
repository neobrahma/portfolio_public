package com.neobrahma.portfolio.data.database.room.tree

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.neobrahma.portfolio.data.database.room.table.Project
import com.neobrahma.portfolio.data.database.room.table.Stack
import com.neobrahma.portfolio.data.database.room.table.Task
import com.neobrahma.portfolio.data.database.room.table.crossref.ProjectStackCrossRef

data class ProjectWithStacksAndTasks(
    @Embedded val project: Project,
    @Relation(
        parentColumn = "projectId",
        entityColumn = "projectId",
        entity = Task::class,
        projection = ["description"]
    )
    val tasks: List<String>,
    @Relation(
        parentColumn = "projectId",
        entityColumn = "stackId",
        associateBy = Junction(ProjectStackCrossRef::class)
    )
    val stacks: List<Stack>

)