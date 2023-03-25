package com.neobrahma.portfolio.data.database

import androidx.test.filters.SmallTest
import com.neobrahma.portfolio.data.SavePortfolioDataSource
import com.neobrahma.portfolio.data.database.room.AppDatabaseTest
import com.neobrahma.portfolio.data.database.room.portfolio.PortfolioTestDao
import com.neobrahma.portfolio.data.database.room.portofolio.PortfolioDao
import com.neobrahma.portfolio.data.database.room.table.*
import com.neobrahma.portfolio.data.database.room.table.crossref.ProjectStackCrossRef
import com.neobrahma.portfolio.data.mock.model.*
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named

@HiltAndroidTest
@SmallTest
class SavePortfolioLocalDataSourceImplTest {
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    @Named("test_db")
    lateinit var database: AppDatabaseTest

    lateinit var portfolioDao: PortfolioDao
    lateinit var portfolioTestDao: PortfolioTestDao

    private lateinit var savePortfolioDataSource: SavePortfolioDataSource

    @Before
    fun setup() {
        hiltRule.inject()
        portfolioDao = database.portfolioDAO()
        portfolioTestDao = database.portfolioTestDAO()

        savePortfolioDataSource = SavePortfolioLocalDataSourceImpl(portfolioDao)
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun addAllCategory() = runBlocking {

        val categoriesDAO = listOf(
            CategoryDAO(100, "test0")
        )
        val expected = listOf(
            Category(100, "test0")
        )

        savePortfolioDataSource.addAllCategory(categoriesDAO)
        val result = portfolioTestDao.getCategories()
        assertEquals("equal", expected, result)
    }

    @Test
    fun addAllStack() = runBlocking {

        val stacksDAO = listOf(
            StackDAO(1, "label1", "iconId1", 1),
            StackDAO(2, "label2", "iconId2", 2)
        )
        val expected = listOf(
            Stack(1, "label1", "iconId1", 1, false),
            Stack(2, "label2", "iconId2", 2, false)
        )

        savePortfolioDataSource.addAllStack(stacksDAO)
        val result = portfolioTestDao.getStacks()
        assertEquals("equal", expected, result)
    }

    @Test
    fun whenAddAllCompany_ShouldCallInsertCompany() = runBlocking {

        val companiesDAO = getMockCompanyWithProjectNoClient()
        val expected = listOf(
            Company(1, "Atos", "logo_default", "09/13 - 02/14", "Rennes")
        )

        savePortfolioDataSource.addAllCompany(companiesDAO)
        val result = portfolioTestDao.getCompanies()
        assertEquals("equal", expected, result)
    }

    @Test
    fun whenAddAllCompany_ShouldCallInsertProject() = runBlocking {

        val companiesDAO = getMockCompanyWithProjectNoClient()
        val expected = listOf(
            Project(0, 1, "CNT", "logo_default", "J2EE Developer", "Application bla bla bla")
        )

        savePortfolioDataSource.addAllCompany(companiesDAO)
        val result = portfolioTestDao.getProjects()
        assertEquals("equal", expected, result)
    }

    @Test
    fun whenAddAllCompany_ShouldCallInsertTask() = runBlocking {

        val companiesDAO = getMockCompanyWithProjectNoClient()
        val expected = listOf(
            Task(1, 0, "dev"),
            Task(2, 0, "fix"),
            Task(3, 0, "etc, etc")
        )

        savePortfolioDataSource.addAllCompany(companiesDAO)
        val result = portfolioTestDao.getTasks()
        assertEquals("equal", expected, result)
    }

    @Test
    fun whenAddAllCompany_ShouldCallInsertProjectStackCrossRef() = runBlocking {

        val companiesDAO = getMockCompanyWithProjectNoClient()
        val expected = listOf(
            ProjectStackCrossRef(0, 1),
            ProjectStackCrossRef(0, 5),
            ProjectStackCrossRef(0, 7),
            ProjectStackCrossRef(0, 9),
            ProjectStackCrossRef(0, 32),
        )

        savePortfolioDataSource.addAllCompany(companiesDAO)
        val result = portfolioTestDao.getProjectStackCrossRefs()
        assertEquals("equal", expected, result)
    }

    @Test
    fun whenAddAllCompany_ifNotClients_shouldBeEmpty() = runBlocking {

        val companiesDAO = getMockCompanyWithProjectNoClient()
        val expected = emptyList<Client>()

        savePortfolioDataSource.addAllCompany(companiesDAO)
        val result = portfolioTestDao.getClient()
        assertEquals("equal", expected, result)
    }

    @Test
    fun whenAddAllCompany_withClient_shouldCallInsertClient() = runBlocking {

        val companiesDAO = getMockCompanyWithClientNoProject()
        val expected = listOf(
            Client(1015, 1, "Renault Digital", "logo_default", "08/22 - 11/22", "Paris")
        )

        savePortfolioDataSource.addAllCompany(companiesDAO)
        val result = portfolioTestDao.getClient()
        assertEquals("equal", expected, result)
    }

    @Test
    fun whenAddAllCompany_withClient_shouldCallInsertProject() = runBlocking {

        val companiesDAO = getMockCompanyWithClientNoProject()
        val expected = listOf(
            Project(
                24,
                1015,
                "MyBrand",
                "logo_default",
                "Android Developer",
                "Application bla bla bla"
            )
        )

        savePortfolioDataSource.addAllCompany(companiesDAO)
        val result = portfolioTestDao.getProjects()
        assertEquals("equal", expected, result)
    }

    @Test
    fun whenAddAllCompany_withClient_shouldCallInsertTask() = runBlocking {

        val companiesDAO = getMockCompanyWithClientNoProject()
        val expected = listOf(
            Task(1, 24, "dev"),
            Task(2, 24, "fix"),
            Task(3, 24, "etc, etc")
        )

        savePortfolioDataSource.addAllCompany(companiesDAO)
        val result = portfolioTestDao.getTasks()
        assertEquals("equal", expected, result)
    }

    @Test
    fun whenAddAllCompany_withClient_shouldCallInsertProjectStackCrossRef() = runBlocking {

        val companiesDAO = getMockCompanyWithClientNoProject()
        val expected = listOf(
            ProjectStackCrossRef(24, 2),
            ProjectStackCrossRef(24, 4),
            ProjectStackCrossRef(24, 6)
        )

        savePortfolioDataSource.addAllCompany(companiesDAO)
        val result = portfolioTestDao.getProjectStackCrossRefs()
        assertEquals("equal", expected, result)
    }

    private fun getMockCompanyWithClientNoProject() =
        listOf(
            CompanyDAO(
                companyId = 1,
                name = "Atos",
                logo = "logo_default",
                date = "09/13 - 02/14",
                city = "Rennes",
                clients = listOf(
                    ClientDAO(
                        id = 15,
                        name = "Renault Digital",
                        logo = "logo_default",
                        date = "08/22 - 11/22",
                        city = "Paris",
                        projects = listOf(
                            ProjectDAO(
                                id = 24,
                                name = "MyBrand",
                                logo = "logo_default",
                                role = "Android Developer",
                                context = "Application bla bla bla",
                                tasks = listOf(
                                    "dev",
                                    "fix",
                                    "etc, etc"
                                ),
                                stacks = listOf(
                                    2,
                                    4,
                                    6
                                )
                            )
                        )
                    )
                ),
                projects = emptyList()
            )
        )

    private fun getMockCompanyWithProjectNoClient() =
        listOf(
            CompanyDAO(
                companyId = 1,
                name = "Atos",
                logo = "logo_default",
                date = "09/13 - 02/14",
                city = "Rennes",
                clients = emptyList(),
                projects = listOf(
                    ProjectDAO(
                        id = 0,
                        name = "CNT",
                        logo = "logo_default",
                        role = "J2EE Developer",
                        context = "Application bla bla bla",
                        tasks = listOf(
                            "dev",
                            "fix",
                            "etc, etc"
                        ),
                        stacks = listOf(1, 5, 7, 9, 32)
                    )
                )
            )
        )

}
