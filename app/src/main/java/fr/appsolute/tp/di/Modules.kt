package fr.appsolute.tp.di

import fr.appsolute.tp.RickAndMortyApplication
import fr.appsolute.tp.data.database.DatabaseManager
import fr.appsolute.tp.data.networking.HttpClientManager
import fr.appsolute.tp.data.networking.api.CharacterApi
import fr.appsolute.tp.data.networking.api.EpisodeApi
import fr.appsolute.tp.data.networking.createApi
import fr.appsolute.tp.data.repository.CharacterRepository
import fr.appsolute.tp.data.repository.EpisodeRepository
import fr.appsolute.tp.ui.viewmodel.CharacterViewModel
import fr.appsolute.tp.ui.viewmodel.EpisodeViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val dataModule = module {
    single {
        DatabaseManager.getInstance(androidContext().applicationContext as RickAndMortyApplication)
    }
    single {
        get<DatabaseManager>().database.episodeDao
    }
}

val networkingModule = module {
    single {
        HttpClientManager.create()
    }
    single<CharacterApi> {
        get<HttpClientManager>().createApi()
    }
    single<EpisodeApi> {
        get<HttpClientManager>().createApi()
    }
}

val repositoryModule = module {
    single {
        CharacterRepository.newInstance(get())
    }
    single {
        EpisodeRepository.newInstance(
            dao = get(),
            api = get()
        )
    }
}

val viewModelModule = module {
    viewModel {
        CharacterViewModel(get())
    }
    viewModel {
        EpisodeViewModel(get())
    }
}