package io.github.ziginsider.daggerproject.dagger

import android.content.Context
import com.jakewharton.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient

@Module
class PicassoModule {

    @Provides
    fun picasso(context: Context, okHttp3Downloader: OkHttp3Downloader) = Picasso.Builder(context)
            .downloader(okHttp3Downloader)
            .build()

    @Provides
    fun okHttp3Downloader(okHttpClient: OkHttpClient) = OkHttp3Downloader(okHttpClient)
}