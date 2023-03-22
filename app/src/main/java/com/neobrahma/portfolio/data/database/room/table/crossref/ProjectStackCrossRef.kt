package com.neobrahma.portfolio.data.database.room.table.crossref

import androidx.room.Entity

@Entity(primaryKeys = ["projectId", "stackId"])
data class ProjectStackCrossRef(
    val projectId: Int,
    val stackId: Int
)