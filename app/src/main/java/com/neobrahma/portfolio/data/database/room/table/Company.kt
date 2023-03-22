package com.neobrahma.portfolio.data.database.room.table

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Company(
    @PrimaryKey val companyId: Int,
    val name: String,
    val logo: String,
    val date: String,
    val city: String
)