package kz.mechta.core_data.di

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import kz.mechta.core_data.data.db.AppDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

actual val databaseDriverModule: Module = module {
    single<SqlDriver> {
        AndroidSqliteDriver(AppDatabase.Schema, androidContext(), "app.db")
    }
}