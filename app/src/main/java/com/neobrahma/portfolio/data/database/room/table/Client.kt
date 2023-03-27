package com.neobrahma.portfolio.data.database.room.table

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Client(
    @PrimaryKey val clientId: Int,
    val companyId: Int,
    val name: String,
    val logo: String,
    val date: String,
    val city: String,
)