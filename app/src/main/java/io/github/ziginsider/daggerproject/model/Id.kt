package io.github.ziginsider.daggerproject.model

import com.google.gson.annotations.SerializedName

class Id(@SerializedName("name") val name: String,
         @SerializedName("value") val value: String)
