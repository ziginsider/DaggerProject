package io.github.ziginsider.daggerproject.dagger

import dagger.Component
import io.github.ziginsider.daggerproject.MainActivity

@Component(modules = [MainActivityModule::class], dependencies = [RandomUserComponent::class])
@MainActivityScope
interface MainActivityComponent {
    fun injectMainActivity(mainActivity: MainActivity)
}