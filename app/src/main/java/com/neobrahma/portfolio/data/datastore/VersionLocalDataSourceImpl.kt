package com.neobrahma.portfolio.data.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import com.neobrahma.portfolio.data.VersionLocalDataSource
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class VersionLocalDataSourceImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : VersionLocalDataSource {

    private val VERSION_REMOTE_DB = intPreferencesKey("versionRemoteDB")

    override suspend fun getVersion(): Int {
        return dataStore.data.first()[VERSION_REMOTE_DB] ?: 0
    }

    override suspend fun updateVersion() {
        dataStore.edit { settings ->
            val currentCounterValue = settings[VERSION_REMOTE_DB] ?: 0
            settings[VERSION_REMOTE_DB] = currentCounterValue + 1
        }
    }
}