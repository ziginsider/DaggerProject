package io.github.ziginsider.daggerproject.dagger

import android.content.Context
import com.jakewharton.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient

@Module(includes = [OkHttpClientModule::class])
class PicassoModule {

    @RandomUserApplicationScope
    @Provides
    fun picasso(context: Context, okHttp3Downloader: OkHttp3Downloader): Picasso
            = Picasso.Builder(context)
            .downloader(okHttp3Downloader)
            .build()

    @Provides
    fun okHttp3Downloader(okHttpClient: OkHttpClient): OkHttp3Downloader
            = OkHttp3Downloader(okHttpClient)
}