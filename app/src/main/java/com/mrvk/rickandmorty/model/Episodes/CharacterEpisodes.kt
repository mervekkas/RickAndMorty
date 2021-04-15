package com.mrvk.rickandmorty.model.Episodes

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CharacterEpisodes(
    @SerializedName("info")
    @Expose
    val info: InfoEpisodes? = null,
    @SerializedName("results")
    @Expose
    val results: List<ResultEpisodes>? = null
) {
}