package foit.product.loranalyst

import android.app.Application
import foit.product.loranalyst.di.repoModule
import foit.product.loranalyst.di.retrofitModule
import foit.product.loranalyst.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level


class LorAnalystApplication: Application() {

    private var instance: LorAnalystApplication? = null

    override fun onCreate() {
        super.onCreate()

        instance = this

        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@LorAnalystApplication)
            modules(listOf(retrofitModule, viewModelModule, repoModule))
        }
    }

    fun getInstance(): LorAnalystApplication? {
        return instance
    }
}