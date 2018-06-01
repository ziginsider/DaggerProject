package io.github.ziginsider.daggerproject.service

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RandomUserApi {

    @GET("api")
    fun getRandomUsers (@Query("results") size: Int): Call<RandomUsers>
}