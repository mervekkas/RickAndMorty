package com.mrvk.rickandmorty.model.Episodes

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ResultEpisodes(
    @SerializedName("id")
    @Expose
    val id: Int? = null,

    @SerializedName("name")
    @Expose
    val name: String? = null,

    @SerializedName("air_date")
    @Expose
    val airDate: String? = null,

    @SerializedName("episode")
    @Expose
    val episode: String? = null,

    @SerializedName("characters")
    @Expose
    val characters: List<String>? = null,

    @SerializedName("url")
    @Expose
    val url: String? = null,

    @SerializedName("created")
    @Expose
    val created: String? = null
) {
}