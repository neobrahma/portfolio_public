package com.neobrahma.portfolio.data.database

import com.neobrahma.portfolio.data.InformationDataSource
import com.neobrahma.portfolio.data.database.room.information.InformationDao
import com.neobrahma.portfolio.domain.model.InformationData
import com.neobrahma.portfolio.domain.model.StackStatisticalData
import javax.inject.Inject

class InformationLocalDataSourceImpl @Inject constructor(
    private val room: InformationDao
) : InformationDataSource {

    override suspend fun getStackStatistical(): InformationData {
        val quantityProject = room.countProjects()

        val stats: List<StackStatisticalData> = room.getCategories().map { category ->
            StackStatisticalData(
                category.label,
                room.getStaticalByCategory(category.categoryId).map {
                    it.label to it.count
                }
            )
        }

        return InformationData(
            quantityProject,
            stats
        )
    }

}

