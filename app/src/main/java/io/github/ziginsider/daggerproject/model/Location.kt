package io.github.ziginsider.daggerproject.model

import com.google.gson.annotations.SerializedName

class Location(@SerializedName("street") val street: String,
               @SerializedName("city") val city: String,
               @SerializedName("state") val state: String,
               @SerializedName("postcode") val postcode: String)
