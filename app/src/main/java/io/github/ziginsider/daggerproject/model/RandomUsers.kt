package io.github.ziginsider.daggerproject.model

import com.google.gson.annotations.SerializedName

class RandomUsers(@SerializedName("results") val result: List<Results>? = null,
                  @SerializedName("info") val info: Info)