package com.mrvk.rickandmorty.service

import com.mrvk.rickandmorty.model.Character.Character
import io.reactivex.Single
import retrofit2.Retrofit.*
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ApiService {

    private val BASE_URL = "https://rickandmortyapi.com/"
    private val api = Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(ApiInterface::class.java)

    fun getCharacter(pageNo: Int) : Single<Character> {
        return api.getCharacter(pageNo)
    }
}