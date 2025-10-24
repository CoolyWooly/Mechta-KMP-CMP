package kz.mechta.core_data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import kz.mechta.core_data.data.datastore.createDataStore
import kz.mechta.core_data.data.datastore.dataStoreFileName

fun createDataStore(context: Context): DataStore<Preferences> = createDataStore(
    producePath = { context.filesDir.resolve(dataStoreFileName).absolutePath }
)