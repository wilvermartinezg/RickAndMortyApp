package com.wilvermartinez.rickandmortyapp.services

import com.wilvermartinez.rickandmortyapp.models.CharacterModel
import com.wilvermartinez.rickandmortyapp.models.CharacterResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RickAndMortyHttpService {

    @GET("character")
    suspend fun getCharacters(
        @Query("page") page: Int,
        @Query("name") name: String? = null
    ): CharacterResponse

    @GET("character/{id}")
    suspend fun getCharacterById(@Path("id") id: Int): CharacterModel

}
