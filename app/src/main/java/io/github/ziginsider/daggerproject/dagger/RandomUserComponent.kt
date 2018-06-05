package io.github.ziginsider.daggerproject.dagger

import com.squareup.picasso.Picasso
import dagger.Component
import io.github.ziginsider.daggerproject.service.RandomUserApi

@RandomUserApplicationScope
@Component(modules = [RandomUserModule::class, PicassoModule::class])
interface RandomUserComponent {
    fun getRandomUserService(): RandomUserApi
    fun getPicasso() : Picasso
}