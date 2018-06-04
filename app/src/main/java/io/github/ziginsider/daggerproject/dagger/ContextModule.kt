package io.github.ziginsider.daggerproject.dagger

import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class ContextModule(val context: Context) {

    @Provides
    fun context(context: Context) = context.applicationContext
}