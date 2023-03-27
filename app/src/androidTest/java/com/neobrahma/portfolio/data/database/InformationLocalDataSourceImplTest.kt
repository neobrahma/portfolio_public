package com.neobrahma.portfolio.data.database

import androidx.test.filters.SmallTest
import com.neobrahma.portfolio.data.InformationDataSource
import com.neobrahma.portfolio.data.database.room.AppDatabaseTest
import com.neobrahma.portfolio.data.database.room.information.InformationDao
import com.neobrahma.portfolio.data.database.room.table.Category
import com.neobrahma.portfolio.data.mock.model.CategoryDAO
import com.neobrahma.portfolio.data.mock.model.CompanyDAO
import com.neobrahma.portfolio.data.mock.model.ProjectDAO
import com.neobrahma.portfolio.data.mock.model.StackDAO
import com.neobrahma.portfolio.domain.model.InformationData
import com.neobrahma.portfolio.domain.model.StackStatisticalData
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named

@HiltAndroidTest
@SmallTest
class InformationLocalDataSourceImplTest {
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    @Named("test_db")
    lateinit var database: AppDatabaseTest

    lateinit var informationDao: InformationDao

    private lateinit var informationDataSource: InformationDataSource

    @Before
    fun setup() {
        hiltRule.inject()

        informationDao = database.informationDao()
        informationDataSource = InformationLocalDataSourceImpl(informationDao)

        val portfolioDao = database.portfolioDAO()
        val savePortfolioDataSource = SavePortfolioLocalDataSourceImpl(portfolioDao)

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
            savePortfolioDataSource.addAllCompany(getMockCompanyWithProjectNoClient())
        }
    }

    @Test
    fun getStackStatistical() = runBlocking {
        val result = informationDataSource.getStackStatistical()
        val expected = InformationData(
            2,
            listOf(
                StackStatisticalData(
                    "category1",
                    listOf(
                        "stack1" to 1
                    )
                ),
                StackStatisticalData(
                    "category2",
                    listOf(
                        "stack2" to 2,
                        "stack3" to 1
                    )
                )

            )
        )

        Assert.assertEquals(expected, result)
    }


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
                        stacks = listOf(3,2)
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