package com.nirwashh.android.jokeapp.viewmodel

import com.nirwashh.android.jokeapp.domain.Joke
import com.nirwashh.android.jokeapp.domain.JokeFailure
import com.nirwashh.android.jokeapp.model.Model
import com.nirwashh.android.jokeapp.model.ResultCallback

class ViewModel(private val model: Model) {
    private var callback: TextCallback? = null

    fun init(callback: TextCallback) {
        this.callback = callback
        model.init(object : ResultCallback {
            override fun provideSuccess(data: Joke) = callback.provideText(data.getJokeUi())


            override fun provideError(error:JokeFailure) = callback.provideText(error.getMessage())
        })
    }

    fun getJoke() {
        model.getJoke()
    }

    fun clear() {
        callback = null
        model.clear()
    }
}

interface TextCallback {
    fun provideText(text: String)
}