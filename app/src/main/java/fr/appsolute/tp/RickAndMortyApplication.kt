package fr.appsolute.tp

import android.app.Application
import fr.appsolute.tp.di.dataModule
import fr.appsolute.tp.di.networkingModule
import fr.appsolute.tp.di.repositoryModule
import fr.appsolute.tp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

/**
 * New entry point of the application (Referenced in the manifests)
 */
class RickAndMortyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@RickAndMortyApplication)
            if (BuildConfig.DEBUG) androidLogger(level = Level.DEBUG)
            loadKoinModules(
                listOf(
                    dataModule,
                    networkingModule,
                    repositoryModule,
                    viewModelModule
                )
            )
        }
    }
}