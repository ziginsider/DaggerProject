package io.github.ziginsider.daggerproject.dagger

import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class ContextModule(val context: Context) {

    @ForApplication
    @RandomUserApplicationScope
    @Provides
    fun context(): Context = context.applicationContext
}
