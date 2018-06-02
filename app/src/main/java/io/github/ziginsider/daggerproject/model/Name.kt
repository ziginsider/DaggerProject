package io.github.ziginsider.daggerproject.model

import com.google.gson.annotations.SerializedName

class Name(@SerializedName("title") val title: String,
           @SerializedName("first") val first: String,
           @SerializedName("last") val last: String)