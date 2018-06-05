package io.github.ziginsider.daggerproject.dagger

import dagger.Component
import io.github.ziginsider.daggerproject.adapter.RecyclerViewAdapter
import io.github.ziginsider.daggerproject.service.RandomUserApi

@Component(dependencies = [RandomUserComponent::class])
@MainActivityScope
interface MainActivityComponent {
    fun getRandomUserAdapter(): RecyclerViewAdapter
    fun getRandomUserService(): RandomUserApi
}