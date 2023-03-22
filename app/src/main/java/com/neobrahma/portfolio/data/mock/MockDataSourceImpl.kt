package com.neobrahma.portfolio.data.mock

import com.neobrahma.portfolio.data.TreeDataSource
import com.neobrahma.portfolio.data.mock.model.*
import com.neobrahma.portfolio.domain.model.*
import javax.inject.Inject

class MockDataSourceImpl @Inject constructor() : TreeDataSource {

    override fun getCompanies(): List<CompanyDAO> {
        return testCompany
//            .map {
//            convertToDataBis(it)
//        }
    }

    override fun getCompanyBy(idCompany: Int): CompanyData {
        TODO("Not yet implemented")
    }

    override fun getProjectBy(idCompany: Int, idProject: Int): CompanyData {
        TODO("Not yet implemented")
    }

    //    override fun getCompanyBy(idCompany: Int): CompanyData {
//        return convertToData(
//            companies.first { it.id == idCompany }
//        )
//    }
//
//    override fun getProjectBy(idCompany: Int, idProject: Int): CompanyData {
//        val companyDAO = companies.first { it.id == idCompany }
//
//        val projectDAO = companyDAO.projects.first { it.id == idProject }
//
//        return CompanyData(
//            companyId = companyDAO.id,
//            name = companyDAO.name,
//            logo = companyDAO.logo,
//            date = companyDAO.date,
//            city = companyDAO.city,
//            clients = listOf(),
//            projects = listOf(
//                convertToData(projectDAO)
//            )
//        )
//    }

    override fun getCategories(): List<CategoryData> {
        val result = mutableListOf<CategoryData>()
        categories.forEach { category ->
            result.add(
                CategoryData(
                    categoryId = category.id,
                    label = category.label,
                    stacks = stacks
                        .filter { it.idCategory == category.id }
                        .map { convertToData(it) }
                )
            )
        }
        return result
    }

    override fun getStacks(): List<StackData> {
        return stacks.map { convertToData(it) }
    }

    private fun convertToData(dao: StackDAO): StackData {
        return StackData(
            stackId = dao.id,
            label = dao.label,
            iconId = dao.idIcon,
            categoryId = dao.idCategory
        )
    }

    private fun convertToData(dao: ProjectDAO): ProjectData {
        return ProjectData(
            id = dao.id,
            name = dao.name,
            logo = dao.logo,
            role = dao.role,
            context = dao.context,
            tasks = dao.tasks,
            categories = mapOf<Int, String>(),
            stacks = listOf()// dao.stacks
        )
    }

    private fun convertToData(dao: ClientDAO): ClientData {
        return ClientData(
            clientId = dao.id,
            name = dao.name,
            logo = dao.logo,
            date = dao.date,
            city = dao.city,
            projects = dao.projects.map { convertToData(it) }
        )
    }

    private fun convertToData(dao: CompanyDAO): CompanyData {
        val projects = dao.projects.map {
            convertToData(it)
        }

        val clients = dao.clients.map {
            convertToData(it)
        }

        return CompanyData(
            companyId = dao.companyId,
            name = dao.name,
            logo = dao.logo,
            date = dao.date,
            city = dao.city,
            clients = clients.toList(),
            projects = projects.toList()
        )
    }
}