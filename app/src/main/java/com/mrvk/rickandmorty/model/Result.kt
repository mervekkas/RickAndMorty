package com.mrvk.rickandmorty.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("id")
    @Expose
    var id: Int?,

    @SerializedName("name")
    @Expose
    val name: String?,

    @SerializedName("status")
    @Expose
    val status: String?,

    @SerializedName("species")
    @Expose
    val species: String?,

    @SerializedName("type")
    @Expose
    val type: String?,

    @SerializedName("gender")
    @Expose
    val gender: String?,

    @SerializedName("image")
    @Expose
    val image: String?,

    @SerializedName("episode")
    @Expose
    val episode: List<String>?,

    @SerializedName("url")
    @Expose
    val url: String?,

    @SerializedName("created")
    @Expose
    var created: String?
) {
}