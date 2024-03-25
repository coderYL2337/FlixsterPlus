package com.example.flixsterplus

import java.io.Serializable
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.*

data class TrendingPeopleResponse(
    @SerializedName("results")
    val results: List<Star>
):Serializable

data class Star(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("profile_path")
    val profilePath: String?,
    @SerializedName("known_for")
    val knownFor: List<KnownFor>
):Serializable

data class KnownFor(
    @SerializedName("title")
    val title: String?,
    @SerializedName("overview")
    val overview: String?,
    @SerializedName("poster_path")
    val posterPath: String?
):Serializable