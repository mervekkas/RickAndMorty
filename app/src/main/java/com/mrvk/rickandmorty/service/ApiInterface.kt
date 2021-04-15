package com.mrvk.rickandmorty.service

import com.mrvk.rickandmorty.model.Character.Character
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    //https://rickandmortyapi.com/api/character

    @GET("api/character")
    fun getCharacter(@Query("page") page:Int) : Single<Character>
}