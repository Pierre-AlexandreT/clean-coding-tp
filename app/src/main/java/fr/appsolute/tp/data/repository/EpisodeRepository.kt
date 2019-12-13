package fr.appsolute.tp.data.repository

import fr.appsolute.tp.data.database.dao.EpisodeDao
import fr.appsolute.tp.data.model.Episode
import fr.appsolute.tp.data.model.PaginatedResult
import fr.appsolute.tp.data.networking.api.EpisodeApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

private class EpisodeRepositoryImpl(
    private val api: EpisodeApi,
    private val dao: EpisodeDao
) : EpisodeRepository {

    override suspend fun getAllEpisode(filterId: List<Int>): List<Episode> {
        return withContext(Dispatchers.IO) {
            val firstResponse = api.getAllEpisode(1)
            val (firstInformation, firstResult) = firstResponse.unwrap()
            val episodes = if (dao.getCount() != firstInformation.count) {
                val allEpisode = firstResult.toMutableList()
                try {
                    var page = 2
                    while (true) {
                        val (information, result) = api.getAllEpisode(page).unwrap()
                        allEpisode.addAll(result)
                        if (information.next.isNotEmpty()) {
                            page++
                        } else break
                    }
                    dao.insertAll(*allEpisode.toTypedArray())
                    allEpisode
                } catch (e: Exception) {
                    e.printStackTrace()
                    emptyList<Episode>()
                }
            } else dao.selectAll()

            return@withContext withContext(Dispatchers.Default) {
                episodes.filter { it.id in filterId }
            }
        }
    }

    // Unwrap a response to get information and result
    private fun Response<PaginatedResult<Episode>>.unwrap(): Pair<PaginatedResult.Information, List<Episode>> {
        check(this.isSuccessful) { "Response is not a success: code = ${this.code()}" }
        val paginatedResult =
            this.body() ?: throw IllegalStateException("Body is null")
        return with(paginatedResult) { information to results }
    }
}

interface EpisodeRepository {

    /**
     * Synchronize all episode between database and api, then return filtered list
     */
    suspend fun getAllEpisode(filterId: List<Int>): List<Episode>

    companion object {
        /**
         * Singleton for the interface [EpisodeRepository]
         */

        fun newInstance(api: EpisodeApi, dao: EpisodeDao): EpisodeRepository =
            EpisodeRepositoryImpl(api, dao)
    }

}

