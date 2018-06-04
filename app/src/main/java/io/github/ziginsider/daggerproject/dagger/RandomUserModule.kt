package io.github.ziginsider.daggerproject.dagger

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import io.github.ziginsider.daggerproject.service.RandomUserApi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class RandomUserModule {

    @Provides
    fun randomUserApi(retrofit: Retrofit) = retrofit.create(RandomUserApi::class.java)

    @Provides
    fun retrofit(okHttpClient: OkHttpClient, gsonConverterFactory: GsonConverterFactory, gson: Gson)
            = Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("https://randomuser.me/")
            .addConverterFactory(gsonConverterFactory)
            .build()

    @Provides
    fun gson() = GsonBuilder().create()

    @Provides
    fun gsonConverterFactory(gson: Gson) = GsonConverterFactory.create(gson)
}