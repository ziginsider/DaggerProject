package io.github.ziginsider.daggerproject.dagger

import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(val context: Context) {

    @RandomUserApplicationScope
    @Provides
    fun context() = context
}
