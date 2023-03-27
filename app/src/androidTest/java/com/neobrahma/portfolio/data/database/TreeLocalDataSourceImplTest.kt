package com.neobrahma.portfolio.data.database

import com.neobrahma.portfolio.data.FilterDataSource
import com.neobrahma.portfolio.data.SavePortfolioDataSource
import com.neobrahma.portfolio.data.TreeDataSource
import com.neobrahma.portfolio.data.database.room.AppDatabaseTest
import com.neobrahma.portfolio.data.mock.model.*
import com.neobrahma.portfolio.domain.model.ClientData
import com.neobrahma.portfolio.domain.model.CompanyData
import com.neobrahma.portfolio.domain.model.ProjectData
import com.neobrahma.portfolio.domain.model.StackData
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.*
import javax.inject.Inject
import javax.inject.Named

@HiltAndroidTest
class TreeLocalDataSourceImplTest {
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    @Named("test_db")
    lateinit var database: AppDatabaseTest

    private lateinit var treeDataSource: TreeDataSource
    private lateinit var filterDataSource: FilterDataSource
    private lateinit var savePortfolioDataSource: SavePortfolioDataSource

    @Before
    fun setup() {
        hiltRule.inject()

        filterDataSource = FilterLocalDataSourceImpl(database.filterDAO())
        treeDataSource = TreeLocalDataSourceImpl(database.treeDAO())

        val portfolioDao = database.portfolioDAO()
        savePortfolioDataSource = SavePortfolioLocalDataSourceImpl(portfolioDao)

        val stacksDAO = listOf(
            StackDAO(1, "stack1", "iconId1", 1),
            StackDAO(2, "stack2", "iconId2", 2),
            StackDAO(3, "stack3", "iconId2", 2)
        )
        val categoriesDAO = listOf(
            CategoryDAO(1, "category1"),
            CategoryDAO(2, "category2")
        )
        runBlocking {
            savePortfolioDataSource.addAllStack(stacksDAO)
            savePortfolioDataSource.addAllCategory(categoriesDAO)
        }
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun getCompanies_withNoClient() {
        runBlocking {
            savePortfolioDataSource.addAllCompany(getMockCompanyWithProjectNoClient())

            val expected = listOf(
                CompanyData(
                    2,
                    "Atos",
                    "logo_default",
                    "09/13 - 02/14",
                    "Rennes",
                    emptyList(),
                    listOf(
                        ProjectData(
                            2,
                            "CNT",
                            "logo_default",
                            "J2EE Developer",
                            "Application bla bla bla",
                            listOf(
                                "dev",
                                "fix",
                                "etc, etc"
                            ),
                            mapOf(
                                2 to "category2"
                            ),
                            listOf(
                                StackData(2, "stack2", "iconId2", 2),
                                StackData(3, "stack3", "iconId2", 2)
                            )
                        )
                    )
                ),
                CompanyData(
                    1,
                    "Atos",
                    "logo_default",
                    "09/13 - 02/14",
                    "Rennes",
                    emptyList(),
                    listOf(
                        ProjectData(
                            1,
                            "CNT",
                            "logo_default",
                            "J2EE Developer",
                            "Application bla bla bla",
                            listOf(
                                "dev",
                                "fix",
                                "etc, etc"
                            ),
                            mapOf(
                                1 to "category1",
                                2 to "category2"
                            ),
                            listOf(
                                StackData(1, "stack1", "iconId1", 1),
                                StackData(2, "stack2", "iconId2", 2)
                            )
                        )
                    )
                )
            )

            val result = treeDataSource.getCompanies().first()
            Assert.assertEquals("equal", expected, result)
        }
    }

    @Test
    fun getCompanies_withClient() {
        runBlocking {
            savePortfolioDataSource.addAllCompany(getMockCompanyWithProjectAndClient())

            val expected = listOf(
                CompanyData(
                    2,
                    "Atos",
                    "logo_default",
                    "09/13 - 02/14",
                    "Rennes",
                    listOf(
                        ClientData(
                            clientId = 2001,
                            name = "client 1",
                            logo = "logo 1",
                            date = "date 1",
                            city = "city 1",
                            listOf(
                                ProjectData(
                                    3,
                                    "CNT",
                                    "logo_default",
                                    "J2EE Developer",
                                    "Application bla bla bla",
                                    listOf(
                                        "dev",
                                        "fix",
                                        "etc, etc"
                                    ),
                                    mapOf(
                                        2 to "category2"
                                    ),
                                    listOf(
                                        StackData(2, "stack2", "iconId2", 2),
                                        StackData(3, "stack3", "iconId2", 2)
                                    )
                                )
                            )
                        )
                    ),
                    listOf(
                        ProjectData(
                            2,
                            "CNT",
                            "logo_default",
                            "J2EE Developer",
                            "Application bla bla bla",
                            listOf(
                                "dev",
                                "fix",
                                "etc, etc"
                            ),
                            mapOf(
                                2 to "category2"
                            ),
                            listOf(
                                StackData(2, "stack2", "iconId2", 2),
                                StackData(3, "stack3", "iconId2", 2)
                            )
                        )
                    )
                ),
                CompanyData(
                    1,
                    "Atos",
                    "logo_default",
                    "09/13 - 02/14",
                    "Rennes",
                    emptyList(),
                    listOf(
                        ProjectData(
                            1,
                            "CNT",
                            "logo_default",
                            "J2EE Developer",
                            "Application bla bla bla",
                            listOf(
                                "dev",
                                "fix",
                                "etc, etc"
                            ),
                            mapOf(
                                1 to "category1",
                                2 to "category2"
                            ),
                            listOf(
                                StackData(1, "stack1", "iconId1", 1),
                                StackData(2, "stack2", "iconId2", 2)
                            )
                        )
                    )
                )
            )

            val result = treeDataSource.getCompanies().first()
            Assert.assertEquals("equal", expected, result)
        }
    }

    @Test
    fun getCompanies_filtered() {
        runBlocking {
            val expected = listOf(
                CompanyData(
                    2,
                    "Atos",
                    "logo_default",
                    "09/13 - 02/14",
                    "Rennes",
                    emptyList(),
                    listOf(
                        ProjectData(
                            2,
                            "CNT",
                            "logo_default",
                            "J2EE Developer",
                            "Application bla bla bla",
                            listOf(
                                "dev",
                                "fix",
                                "etc, etc"
                            ),
                            mapOf(
                                2 to "category2"
                            ),
                            listOf(
                                StackData(2, "stack2", "iconId2", 2, false),
                                StackData(3, "stack3", "iconId2", 2, true)
                            )
                        )
                    )
                )
            )
            savePortfolioDataSource.addAllCompany(getMockCompanyWithProjectNoClient())
            filterDataSource.updateStack(3, true)
            val result = treeDataSource.getCompanies().first()
            Assert.assertEquals("equal", expected, result)
        }
    }

    private fun getMockCompanyWithProjectAndClient() =
        listOf(
            CompanyDAO(
                companyId = 2,
                name = "Atos",
                logo = "logo_default",
                date = "09/13 - 02/14",
                city = "Rennes",
                clients = listOf(
                    ClientDAO(
                        id = 1,
                        name = "client 1",
                        logo = "logo 1",
                        date = "date 1",
                        city = "city 1",
                        projects = listOf(
                            ProjectDAO(
                                id = 3,
                                name = "CNT",
                                logo = "logo_default",
                                role = "J2EE Developer",
                                context = "Application bla bla bla",
                                tasks = listOf(
                                    "dev",
                                    "fix",
                                    "etc, etc"
                                ),
                                stacks = listOf(3, 2)
                            )
                        )
                    )
                ),
                projects = listOf(
                    ProjectDAO(
                        id = 2,
                        name = "CNT",
                        logo = "logo_default",
                        role = "J2EE Developer",
                        context = "Application bla bla bla",
                        tasks = listOf(
                            "dev",
                            "fix",
                            "etc, etc"
                        ),
                        stacks = listOf(3, 2)
                    )
                )
            ),
            CompanyDAO(
                companyId = 1,
                name = "Atos",
                logo = "logo_default",
                date = "09/13 - 02/14",
                city = "Rennes",
                clients = emptyList(),
                projects = listOf(
                    ProjectDAO(
                        id = 1,
                        name = "CNT",
                        logo = "logo_default",
                        role = "J2EE Developer",
                        context = "Application bla bla bla",
                        tasks = listOf(
                            "dev",
                            "fix",
                            "etc, etc"
                        ),
                        stacks = listOf(1, 2)
                    )
                )
            )
        )

    private fun getMockCompanyWithProjectNoClient() =
        listOf(
            CompanyDAO(
                companyId = 2,
                name = "Atos",
                logo = "logo_default",
                date = "09/13 - 02/14",
                city = "Rennes",
                clients = emptyList(),
                projects = listOf(
                    ProjectDAO(
                        id = 2,
                        name = "CNT",
                        logo = "logo_default",
                        role = "J2EE Developer",
                        context = "Application bla bla bla",
                        tasks = listOf(
                            "dev",
                            "fix",
                            "etc, etc"
                        ),
                        stacks = listOf(3, 2)
                    )
                )
            ),
            CompanyDAO(
                companyId = 1,
                name = "Atos",
                logo = "logo_default",
                date = "09/13 - 02/14",
                city = "Rennes",
                clients = emptyList(),
                projects = listOf(
                    ProjectDAO(
                        id = 1,
                        name = "CNT",
                        logo = "logo_default",
                        role = "J2EE Developer",
                        context = "Application bla bla bla",
                        tasks = listOf(
                            "dev",
                            "fix",
                            "etc, etc"
                        ),
                        stacks = listOf(1, 2)
                    )
                )
            )
        )
}