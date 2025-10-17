package kz.mechta

import android.app.Application
import org.koin.android.ext.koin.androidContext

class MainApp: Application() {

    override fun onCreate() {
        super.onCreate()
//        initKoin {
//            androidContext(this@MainApp)
//        }
    }
}