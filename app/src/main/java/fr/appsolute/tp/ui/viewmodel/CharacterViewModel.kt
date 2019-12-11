package fr.appsolute.tp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import fr.appsolute.tp.data.model.Character
import fr.appsolute.tp.data.repository.CharacterRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

open class CharacterViewModel(
    repository: CharacterRepository
) : ViewModel() {

    private var _data = mutableListOf<Int>()

    val data :List<Int>
        get() = _data

    /**
     *  Return the paginated list of character from the API
     */
    val charactersPagedList = repository.getPaginatedList(viewModelScope)

    fun getCharacterDetails(id: Int, onSuccess: (Int) -> Unit) {
        viewModelScope.launch {
            val truc = withContext(Dispatchers.IO){
                // Job (API; BD)
                return@withContext 1
            }
            onSuccess(truc)
        }
    }

    companion object Factory : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return CharacterViewModel(CharacterRepository.instance) as T
        }
    }
}