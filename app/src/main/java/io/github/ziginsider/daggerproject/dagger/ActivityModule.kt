package io.github.ziginsider.daggerproject.dagger

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class ActivityModule(val context: Context) {

    @Named("activity_context")
    @RandomUserApplicationScope
    @Provides
    fun context() = context
}
