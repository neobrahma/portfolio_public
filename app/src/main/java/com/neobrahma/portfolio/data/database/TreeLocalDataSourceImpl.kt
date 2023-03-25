package com.neobrahma.portfolio.data.database

import com.neobrahma.portfolio.data.TreeDataSource
import com.neobrahma.portfolio.data.database.room.tree.ProjectWithStacksAndTasks
import com.neobrahma.portfolio.data.database.room.table.Client
import com.neobrahma.portfolio.data.database.room.table.Company
import com.neobrahma.portfolio.data.database.room.tree.TreeDao
import com.neobrahma.portfolio.domain.model.ClientData
import com.neobrahma.portfolio.domain.model.CompanyData
import com.neobrahma.portfolio.domain.model.ProjectData
import com.neobrahma.portfolio.domain.model.StackData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform
import javax.inject.Inject

class TreeLocalDataSourceImpl @Inject constructor(
    private val room: TreeDao
) : TreeDataSource {

    override suspend fun getCompanies(): Flow<List<CompanyData>> {
        return room.getSelectedStacks()
            .transform { selectedStacks ->
                if (selectedStacks.isNotEmpty()) {
                    val companies = mutableListOf<CompanyData>()
                    val projectIds = room.getProjectFiltered(selectedStacks)
                    val mapProjectToCompany = mutableMapOf<Int, MutableList<ProjectData>>()
                    val mapProjectToClient =
                        mutableMapOf<Int, MutableMap<Int, MutableList<ProjectWithStacksAndTasks>>>()
                    projectIds.forEach { projectId ->
                        val projectRaw = room.getProject(projectId)
                        val project = mapProject(projectRaw)
                        val ownerId = projectRaw.project.ownerId
                        if (ownerId < 1000) {
                            if (mapProjectToCompany.containsKey(ownerId)) {
                                mapProjectToCompany[ownerId]!!.add(project)
                            } else {
                                mapProjectToCompany[ownerId] = mutableListOf(project)
                            }
                        } else {
                            val companyId: Int = ownerId / 1000
                            if (!mapProjectToClient.containsKey(companyId)) {
                                mapProjectToClient[companyId] = mutableMapOf(
                                    ownerId to mutableListOf(projectRaw)
                                )
                            } else {
                                if (mapProjectToClient[companyId]!!.containsKey(ownerId)) {
                                    mapProjectToClient[companyId]!![ownerId]!!.add(projectRaw)
                                } else {
                                    mapProjectToClient[companyId]!![ownerId] =
                                        mutableListOf(projectRaw)
                                }
                            }
                        }
                    }

                    if (mapProjectToCompany.isNotEmpty()) {
                        mapProjectToCompany.forEach { (ownerId, projects) ->
                            val clients = mutableListOf<ClientData>()
                            if (mapProjectToClient.containsKey(ownerId)) {
                                val map = mapProjectToClient[ownerId]
                                map!!.forEach { (clientId, projectRaw) ->
                                    val client = room.getClient(clientId)
                                    clients.add(mapClient(client, projectRaw))
                                }
                            }

                            val company = mapCompany(
                                room.getCompany(ownerId),
                                projects,
                                clients
                            )
                            companies.add(company)
                        }
                    } else {
                        mapProjectToClient.forEach { (companyId, clients) ->
                            val clientResult = clients.map {
                                val client = room.getClient(it.key)
                                mapClient(client, it.value)
                            }

                            val company = mapCompany(
                                room.getCompany(companyId),
                                emptyList(),
                                clientResult
                            )
                            companies.add(company)
                        }
                    }
                    emit(companies.toList())
                } else {
                    val result = findCompanies()
                    emit(result)
                }
            }
    }

    private suspend fun findCompanies(): List<CompanyData> {
        return room.getCompanies().map { company ->
            val clients = room.getClients(company.companyId).map { client ->
                val projects = room.getProjectByOwner(client.clientId)
                mapClient(client, projects)
            }
            val projects = room.getProjectByOwner(company.companyId).map {
                mapProject(it)
            }
            mapCompany(company, projects, clients)
        }
    }

    private suspend fun mapClient(
        client: Client,
        projects: List<ProjectWithStacksAndTasks>
    ): ClientData {
        return ClientData(
            clientId = client.clientId,
            name = client.name,
            logo = client.logo,
            date = client.date,
            city = client.city,
            projects = projects.map {
                mapProject(it)
            }
        )
    }

    private suspend fun mapProject(data: ProjectWithStacksAndTasks): ProjectData {
        val set = data.stacks.map {
            it.categoryId
        }.toSet()

        val result = room.getCategories(set)
        val categories = mutableMapOf<Int, String>()
        result.forEach {
            categories[it.categoryId] = it.label

        }

        return ProjectData(
            id = data.project.projectId,
            name = data.project.name,
            logo = data.project.logo,
            role = data.project.role,
            context = data.project.context,
            tasks = data.tasks,
            categories = categories,
            stacks = data.stacks.map { stack ->
                StackData(
                    stackId = stack.stackId,
                    label = stack.label,
                    iconId = stack.iconId,
                    categoryId = stack.categoryId,
                    isFavorite = stack.isFiltered
                )
            }
        )
    }

    private fun mapCompany(
        company: Company,
        projects: List<ProjectData>,
        clients: List<ClientData>
    ): CompanyData {
        return CompanyData(
            companyId = company.companyId,
            name = company.name,
            logo = company.logo,
            date = company.date,
            city = company.city,
            clients = clients,
            projects = projects
        )
    }
}