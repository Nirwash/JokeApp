package com.nirwashh.android.jokeapp

import androidx.annotation.DrawableRes
import com.nirwashh.android.jokeapp.viewmodel.DataCallback

class BaseJokeUIModel(text: String) : JokeUIModel(text) {
    override fun getIconResId() = R.drawable.ic_favorite_border
}

class FavoriteJokeUIModel(text: String) : JokeUIModel(text) {
    override fun getIconResId() = R.drawable.ic_favorite
}

class FailedJokeUIModel(text: String) : JokeUIModel(text) {
    override fun getIconResId() = 0
}


abstract class JokeUIModel(private val text: String) {

    private fun text() = text

    @DrawableRes
    protected abstract fun getIconResId(): Int

    fun map(callback: DataCallback) = callback.run {
        provideText(text())
        provideIconRes(getIconResId())
    }
}

interface CacheDataSource {
    fun getJoke(jokeCachedCallback: JokeCachedCallback)
    fun addOrRemove(id: Int, joke: Joke): JokeUIModel
}

interface JokeCachedCallback {
    fun provide(joke: Joke)
    fun fail()
}

interface CloudDataSource {
    fun getJoke(callback: JokeCloudCallback)
}