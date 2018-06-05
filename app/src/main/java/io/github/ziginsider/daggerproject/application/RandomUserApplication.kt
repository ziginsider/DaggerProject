package io.github.ziginsider.daggerproject.application

import android.app.Activity
import android.app.Application
import io.github.ziginsider.daggerproject.dagger.ContextModule
import io.github.ziginsider.daggerproject.dagger.DaggerRandomUserComponent
import io.github.ziginsider.daggerproject.dagger.RandomUserComponent
import timber.log.Timber

class RandomUserApplication : Application() {

    lateinit var randomUserApplicationComponent: RandomUserComponent
        private set

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())

        randomUserApplicationComponent = DaggerRandomUserComponent.builder()
                .contextModule(ContextModule(this))
                .build()
    }

    companion object {
        fun get(activity: Activity) = activity.application as RandomUserApplication
    }
}
