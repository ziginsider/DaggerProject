package io.github.ziginsider.daggerproject.model

import com.google.gson.annotations.SerializedName

class RandomUsers(@SerializedName("results") val results: List<Result>? = null,
                  @SerializedName("info") val info: Info)
