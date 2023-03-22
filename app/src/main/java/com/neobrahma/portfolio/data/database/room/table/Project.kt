package com.neobrahma.portfolio.data.database.room.table

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Project(
    @PrimaryKey val projectId: Int,
    val ownerId : Int,
    val name: String,
    val logo: String,
    val role: String,
    val context: String,
)