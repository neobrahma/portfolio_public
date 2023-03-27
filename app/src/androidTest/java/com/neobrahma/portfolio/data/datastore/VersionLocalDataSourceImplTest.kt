package com.neobrahma.portfolio.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.neobrahma.portfolio.data.VersionLocalDataSource
import kotlinx.coroutines.*
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.File

@RunWith(AndroidJUnit4::class)
class VersionLocalDataSourceImplTest {

    private lateinit var preferencesScope: CoroutineScope
    private lateinit var dataStore: DataStore<Preferences>

    private lateinit var versionLocalDataSource: VersionLocalDataSource

    @Before
    fun setup() {
        preferencesScope = CoroutineScope(Dispatchers.Main + Job())

        dataStore = PreferenceDataStoreFactory.create(scope = preferencesScope) {
            InstrumentationRegistry.getInstrumentation().targetContext.preferencesDataStoreFile(
                "test-preferences-file"
            )
        }

        versionLocalDataSource = VersionLocalDataSourceImpl(dataStore)
    }

    @After
    fun removeDatastore() {
        File(
            ApplicationProvider.getApplicationContext<Context>().filesDir,
            "datastore"
        ).deleteRecursively()

        preferencesScope.cancel()
    }

    @Test
    fun getVersion() = runBlocking {
        val expected = 0
        val result = versionLocalDataSource.getVersion()
        Assert.assertEquals(expected, result)
    }

    @Test
    fun updateVersion() = runBlocking {
        val expected = 1
        versionLocalDataSource.updateVersion()
        val result = versionLocalDataSource.getVersion()
        Assert.assertEquals(expected, result)
    }
}