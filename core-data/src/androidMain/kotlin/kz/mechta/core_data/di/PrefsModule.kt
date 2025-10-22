package kz.mechta.core_data.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import kz.mechta.core_data.createDataStore
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

actual val prefsModule: Module = module {

    single<DataStore<Preferences>> { createDataStore(context = androidContext()) }

}