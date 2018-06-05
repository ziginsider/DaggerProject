package io.github.ziginsider.daggerproject.dagger

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class ContextModule(val context: Context) {

    @Named("application_context")
    @RandomUserApplicationScope
    @Provides
    fun context(): Context = context.applicationContext
}
