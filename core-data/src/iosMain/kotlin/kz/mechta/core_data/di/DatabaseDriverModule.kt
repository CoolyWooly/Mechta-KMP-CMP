package kz.mechta.core_data.di

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import kz.mechta.core_data.data.db.AppDatabase
import org.koin.core.module.Module
import org.koin.dsl.module

actual val databaseDriverModule: Module = module {
    single<SqlDriver> {
        NativeSqliteDriver(AppDatabase.Schema, "app.db")
    }
}