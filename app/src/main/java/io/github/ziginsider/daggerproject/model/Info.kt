package io.github.ziginsider.daggerproject.model

import com.google.gson.annotations.SerializedName

class Info(@SerializedName("seed") val seed: String,
           @SerializedName("results") val results: Int,
           @SerializedName("page") val page: Int,
           @SerializedName("version") val version: String)