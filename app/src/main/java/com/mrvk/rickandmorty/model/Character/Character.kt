package com.mrvk.rickandmorty.model.Character

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Character(
    @SerializedName("info") @Expose
    var info: Info?,
    @SerializedName("results")
    @Expose
    var results: MutableList<Result>?) {
}