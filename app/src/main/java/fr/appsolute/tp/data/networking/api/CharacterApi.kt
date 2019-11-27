package fr.appsolute.tp.data.networking.api

import com.google.gson.JsonObject
import retrofit2.http.GET
import retrofit2.http.Query

interface CharacterApi {

    @GET(GET_ALL_CHARACTER_PATH)
    suspend fun getAllCharacter(
        @Query("page") page: Int
    ): JsonObject

    companion object {
        const val GET_ALL_CHARACTER_PATH = "character/"
    }

}