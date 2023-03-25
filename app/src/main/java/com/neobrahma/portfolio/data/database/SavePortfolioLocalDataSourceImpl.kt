package com.neobrahma.portfolio.data.database

import com.neobrahma.portfolio.data.SavePortfolioDataSource
import com.neobrahma.portfolio.data.database.room.portofolio.PortfolioDao
import com.neobrahma.portfolio.data.database.room.table.*
import com.neobrahma.portfolio.data.database.room.table.crossref.ProjectStackCrossRef
import com.neobrahma.portfolio.data.mock.model.CategoryDAO
import com.neobrahma.portfolio.data.mock.model.CompanyDAO
import com.neobrahma.portfolio.data.mock.model.StackDAO
import javax.inject.Inject

class SavePortfolioLocalDataSourceImpl @Inject constructor(
    private val room: PortfolioDao
) : SavePortfolioDataSource {
    override suspend fun addAllStack(stacks: List<StackDAO>) {
        val result = stacks.map {
            Stack(it.stackId, it.label, it.iconId, it.categoryId, false)
        }
        room.insertAllStack(result)
    }

    override suspend fun addAllCategory(categories: List<CategoryDAO>) {
        val result = categories.map {
            Category(it.categoryId, it.label)
        }
        room.insertAllCategory(result)
    }

    override suspend fun addAllCompany(companies: List<CompanyDAO>) {
        companies.forEach { company ->
            val idCompany = company.companyId
            room.insertCompany(
                Company(company.companyId, company.name, company.logo, company.date, company.city)
            )

            company.projects.forEach { project ->
                val idProject = project.id
                room.insertProject(
                    Project(
                        idProject,
                        idCompany,
                        project.name,
                        project.logo,
                        project.role,
                        project.context
                    )
                )
                project.tasks.forEach { task ->
                    room.insertTask(
                        Task(projectId = idProject, description = task)
                    )
                }
                project.stacks.forEach { idStack ->
                    room.insertProjectStackCrossRef(
                        ProjectStackCrossRef(idProject, idStack)
                    )
                }
            }

            company.clients.forEach { client ->
                val clientId = idCompany * 1000 + client.id
                room.insertClient(
                    Client(
                        clientId,
                        idCompany,
                        client.name, client.logo, client.date, client.city
                    )
                )

                client.projects.forEach { project ->
                    val idProject = project.id
                    room.insertProject(
                        Project(
                            idProject,
                            clientId,
                            project.name,
                            project.logo,
                            project.role,
                            project.context
                        )
                    )
                    project.tasks.forEach { task ->
                        room.insertTask(
                            Task(projectId = idProject, description = task)
                        )
                    }
                    project.stacks.forEach { idStack ->
                        room.insertProjectStackCrossRef(
                            ProjectStackCrossRef(idProject, idStack)
                        )
                    }
                }
            }
        }
    }
}