package com.mrvk.rickandmorty.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mrvk.rickandmorty.model.Character.Character
import com.mrvk.rickandmorty.model.Character.Result
import com.mrvk.rickandmorty.model.Episodes.CharacterEpisodes
import com.mrvk.rickandmorty.model.Episodes.ResultEpisodes
import com.mrvk.rickandmorty.service.ApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class CharacterListViewModel : ViewModel() {

    val characterList = MutableLiveData<List<Result>>()
    val errorMessage = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()
    var pageCount = 1
    var episodesList = mutableListOf<ResultEpisodes>()

    private val characterApiService = ApiService()
    private val disposable = CompositeDisposable()

    fun refreshList() {
        characterDataResponse()
        episodesDataResponse(1)
    }

    private fun characterDataResponse(pageNo: Int? = 1) {
        loading.value = true
        disposable.add(
            characterApiService.getCharacter(pageNo ?: 1)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<Character>() {
                    override fun onSuccess(t: Character) {
                        onSuccesValue(t.results)
                    }

                    override fun onError(e: Throwable) {
                        onErrorValue(e)
                        Log.e("asd", e.localizedMessage)
                    }

                })
        )
    }

    fun episodesDataResponse(pageNo: Int) {
        var _pageNo = pageNo
        if (pageNo < 4)
            disposable.add(characterApiService.getEpisodes(pageNo)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<CharacterEpisodes>() {
                    override fun onSuccess(t: CharacterEpisodes) {
                        t.results?.let { episodesList.addAll(it) }
                        episodesDataResponse(++_pageNo)
                    }

                    override fun onError(e: Throwable) {
                        TODO("Not yet implemented")
                    }

                })
            )
    }

    private fun onErrorValue(e: Throwable) {
        errorMessage.value = true
        loading.value = false
        characterList.value = mutableListOf()
        e.printStackTrace()
    }

    private fun onSuccesValue(t: List<Result>?) {
        errorMessage.value = false
        loading.value = false
        characterList.value = t
    }

    fun getMoreData() {
        if (pageCount <= 34)
            characterDataResponse(pageCount++)
    }

    fun getEpisodesListByCharacter(episode: List<String>?): MutableList<ResultEpisodes> {
        val characterEpisodesList = mutableListOf<ResultEpisodes>() // karakterin oynadığı bölümler
        episode?.forEach {url ->
           var episod =  episodesList.filter { it.url == url }.firstOrNull()
            if (episod != null)
                characterEpisodesList.add(episod)
        }
        return characterEpisodesList
    }
}