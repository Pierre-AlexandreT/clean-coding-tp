package fr.appsolute.tp.data.networking.api

import fr.appsolute.tp.data.model.Character
import fr.appsolute.tp.data.model.PaginatedResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CharacterApi {

    @GET(GET_ALL_CHARACTER_PATH)
    suspend fun getAllCharacter(
        @Query("page") page: Int
    ): Response<PaginatedResult<Character>>

    companion object {
        const val GET_ALL_CHARACTER_PATH = "character/"
    }

}