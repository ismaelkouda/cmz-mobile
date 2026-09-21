package ci.ansut.cmz.app

import android.app.Application
import ci.ansut.cmz.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class CmzApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@CmzApplication)
            androidLogger()
            modules(appModule)
        }
    }
}