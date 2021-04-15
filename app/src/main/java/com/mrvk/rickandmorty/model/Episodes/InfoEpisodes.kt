package com.mrvk.rickandmorty.model.Episodes

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class InfoEpisodes(
    @SerializedName("count")
    @Expose
    val count: Int? = null,

    @SerializedName("pages")
    @Expose
    val pages: Int? = null,

    @SerializedName("next")
    @Expose
    val next: String? = null,

    @SerializedName("prev")
    @Expose
    val prev: Any? = null
) {
}