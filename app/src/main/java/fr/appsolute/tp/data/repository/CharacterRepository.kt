package fr.appsolute.tp.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import fr.appsolute.tp.data.model.Character
import fr.appsolute.tp.data.networking.HttpClientManager
import fr.appsolute.tp.data.networking.api.CharacterApi
import fr.appsolute.tp.data.networking.createApi
import fr.appsolute.tp.data.networking.datasource.CharacterDataSource
import kotlinx.coroutines.CoroutineScope

private class CharacterRepositoryImpl(
    private val api: CharacterApi
) : CharacterRepository {

    private val paginationConfig = PagedList.Config
        .Builder()
        .setEnablePlaceholders(false)
        .setPageSize(20)
        .build()

    override fun getPaginatedList(scope: CoroutineScope): LiveData<PagedList<Character>> {
        return LivePagedListBuilder(
            CharacterDataSource.Factory(api, scope),
            paginationConfig
        ).build()
    }
}

interface CharacterRepository {

    fun getPaginatedList(scope: CoroutineScope): LiveData<PagedList<Character>>

    companion object {
        val instance: CharacterRepository by lazy {
            CharacterRepositoryImpl(HttpClientManager.instance.createApi())
        }
    }

}