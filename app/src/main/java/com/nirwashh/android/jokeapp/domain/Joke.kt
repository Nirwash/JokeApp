package com.nirwashh.android.jokeapp.domain

import androidx.annotation.DrawableRes
import com.nirwashh.android.jokeapp.R
import com.nirwashh.android.jokeapp.api.JokeCloudCallback
import com.nirwashh.android.jokeapp.api.JokeService
import com.nirwashh.android.jokeapp.model.JokeServerModel
import com.nirwashh.android.jokeapp.viewmodel.DataCallback

class BaseJoke(text: String) : Joke(text) {
    override fun getIconResId() = R.drawable.ic_favorite_border
}

class FavoriteJoke(text: String) : Joke(text) {
    override fun getIconResId() = R.drawable.ic_favorite
}

class FailedJoke(text: String) : Joke(text) {
    override fun getIconResId() = 0
}


abstract class Joke(private val text: String) {

    protected fun getJokeUi() = text

    @DrawableRes
    protected abstract fun getIconResId(): Int

    fun map(callback: DataCallback) = callback.run {
        provideText(getJokeUi())
        provideIconRes(getIconResId())
    }
}

interface CacheDataSource {
    fun addOrRemove(id: Int, joke: JokeServerModel): Joke
}

interface CloudDataSource {
    fun getJoke(callback: JokeCloudCallback)
}