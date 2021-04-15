package com.mrvk.rickandmorty.model.Character

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Info(
    @SerializedName("count")
    @Expose
    var count: Int?,

    @SerializedName("pages")
    @Expose
    val pages: Int?,

    @SerializedName("next")
    @Expose
    val next: String?,

    @SerializedName("prev")
    @Expose
    var prev: Any?
) {
}