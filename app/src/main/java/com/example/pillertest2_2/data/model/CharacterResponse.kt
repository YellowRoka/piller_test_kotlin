package com.example.pillertest2_2.data.model

import com.google.gson.annotations.SerializedName
import java.util.Date

data class CharacterResponse(
    @SerializedName("error")   val error   : String?,
    @SerializedName("info")    val info    : InfoModel?,
    @SerializedName("results") val results : List<CharacterModel>?,
)

data class InfoModel(
    @SerializedName("count") val count : Int?,
    @SerializedName("pages") val pages : Int?,
    @SerializedName("next")  val next  : String?,
    @SerializedName("prev")  val prev  : String?,
)

data class CharacterModel(
    @SerializedName("id")         val id         : Int?,
    @SerializedName("name")       val name       : String?,
    @SerializedName("status")     val status     : String?,
    @SerializedName("species")    val species    : String?,
    @SerializedName("type")       val type       : String?,
    @SerializedName("gender")     val gender     : String?,
    @SerializedName("origin")     val origin     : Map<String, String>?,
    @SerializedName("location")   val location   : Map<String, String>?,
    @SerializedName("image")      val image      : String?,
    @SerializedName("episode")    val episode    : List<String>?,
    @SerializedName("url")        val url        : String?,
    @SerializedName("created")    val created    : Date?,
    @SerializedName("isFavorite") var isFavorite : Boolean = false,
)
