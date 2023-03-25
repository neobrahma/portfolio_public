package com.neobrahma.portfolio.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.Job
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class VersionLocalDataSourceImplTest {

//    private val testContext: Context = ApplicationProvider.getApplicationContext()
//    private val testCoroutineDispatcher: TestCoroutineDispatcher =
//        TestCoroutineDispatcher()
//    private val testCoroutineScope =
//        TestCoroutineScope(testCoroutineDispatcher + Job())
//    private val testDataStore: DataStore<Preferences> =
//        PreferenceDataStoreFactory.create(
//            scope = testCoroutineScope,
//            produceFile =
//            { testContext.preferencesDataStoreFile(TEST_DATASTORE_NAME) }
//        )
//
//    private val repository: UserPreferencesRepository =
//        UserPreferencesRepository(testDataStore)

}