package com.nirwashh.android.jokeapp.viewmodel

import androidx.annotation.DrawableRes
import com.nirwashh.android.jokeapp.domain.Joke
import com.nirwashh.android.jokeapp.model.Model
import com.nirwashh.android.jokeapp.model.JokeCallback

class ViewModel(private val model: Model) {
    private var dataCallback: DataCallback? = null
    private val jokeCallback = object : JokeCallback {
        override fun provide(joke: Joke) {
            dataCallback?.let {
                joke.map(it)
            }
        }
    }

    fun init(callback: DataCallback) {
        dataCallback = callback
        model.init(jokeCallback)
    }

    fun getJoke() {
        model.getJoke()
    }

    fun changeJokeStatus() {
        model.changeJokeStatus(jokeCallback)
    }

    fun chooseFavorites(favorites: Boolean) {
        model.chooseDataSource(favorites)
    }

    fun clear() {
        dataCallback = null
        model.clear()
    }
}

interface DataCallback {
    fun provideText(text: String)

    fun provideIconRes(@DrawableRes id: Int)
}