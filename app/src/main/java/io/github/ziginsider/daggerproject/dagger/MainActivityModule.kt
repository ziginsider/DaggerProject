package io.github.ziginsider.daggerproject.dagger

import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides
import io.github.ziginsider.daggerproject.adapter.RecyclerViewAdapter
import io.github.ziginsider.daggerproject.model.Result

@Module
class MainActivityModule(val layoutResId: Int, val clickListener: (Result) -> Unit) {

    @Provides
    @MainActivityScope
    fun randomUserAdapter(picasso: Picasso)
            = RecyclerViewAdapter(layoutResId, picasso, clickListener)
}