package fr.appsolute.tp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import fr.appsolute.tp.data.model.Episode
import fr.appsolute.tp.data.repository.EpisodeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EpisodeViewModel(
    private val repository: EpisodeRepository
) : ViewModel() {

    fun getEpisodes(urls: List<String>, block: OnSuccess<List<Episode>>) {
        viewModelScope.launch {
            val idList = withContext(Dispatchers.Default) {
                urls.map {
                    it.split("/").last().toInt()
                }
            }
            block(repository.getAllEpisode(idList))
        }
    }

    companion object Factory : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return EpisodeViewModel(EpisodeRepository.instance) as T
        }
    }

}