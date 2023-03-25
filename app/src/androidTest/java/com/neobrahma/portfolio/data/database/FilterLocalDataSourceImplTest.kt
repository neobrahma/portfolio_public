package com.neobrahma.portfolio.data.database

import androidx.test.filters.SmallTest
import com.neobrahma.portfolio.data.FilterDataSource
import com.neobrahma.portfolio.data.database.room.AppDatabaseTest
import com.neobrahma.portfolio.data.database.room.filter.FilterDao
import com.neobrahma.portfolio.data.database.room.filter.FilterTestDao
import com.neobrahma.portfolio.data.database.room.table.Stack
import com.neobrahma.portfolio.data.mock.model.CategoryDAO
import com.neobrahma.portfolio.data.mock.model.StackDAO
import com.neobrahma.portfolio.domain.model.CategoryData
import com.neobrahma.portfolio.domain.model.StackData
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.*
import javax.inject.Inject
import javax.inject.Named

@HiltAndroidTest
@SmallTest
class FilterLocalDataSourceImplTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    @Named("test_db")
    lateinit var database: AppDatabaseTest

    lateinit var filterDao: FilterDao

    lateinit var filterTestDao: FilterTestDao

    private lateinit var filterDataSource: FilterDataSource

    @Before
    fun setup() {
        hiltRule.inject()

        filterDao = database.filterDAO()
        filterTestDao = database.filterTestDAO()
        filterDataSource = FilterLocalDataSourceImpl(filterDao)

        val portfolioDao = database.portfolioDAO()
        val savePortfolioDataSource = SavePortfolioLocalDataSourceImpl(portfolioDao)

        val stacksDAO = listOf(
            StackDAO(1, "label1", "iconId1", 1),
            StackDAO(2, "label2", "iconId2", 2)
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
    fun updateStack() = runBlocking {
        val expected = listOf(
            Stack(1, "label1", "iconId1", 1, false),
            Stack(2, "label2", "iconId2", 2, true)
        )

        filterDataSource.updateStack(2, true)
        val result = filterTestDao.getStacks()
        Assert.assertEquals("equal", expected, result)
    }

    @Test
    fun getItemsFilter() = runBlocking {
        val expected = listOf(
            CategoryData(
                1,
                "category1",
                listOf(
                    StackData(1, "label1", "iconId1", 1, false),
                )
            ),
            CategoryData(
                2,
                "category2",
                listOf(
                    StackData(2, "label2", "iconId2", 2, true)
                )
            )
        )

        filterDataSource.updateStack(2, true)
        val result = filterDataSource.getItemsFilter().first()
        Assert.assertEquals("equal", expected, result)
    }

}