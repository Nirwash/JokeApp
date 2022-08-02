package com.nirwashh.android.jokeapp.viewmodel

import com.nirwashh.android.jokeapp.domain.Joke
import com.nirwashh.android.jokeapp.domain.JokeFailure
import com.nirwashh.android.jokeapp.model.Model
import com.nirwashh.android.jokeapp.model.ResultCallback

class ViewModel(private val model: Model<Joke, JokeFailure>) {
    private var callback: TextCallback? = null

    fun init(callback: TextCallback) {
        this.callback = callback
        model.init(object : ResultCallback<Joke, JokeFailure> {
            override fun provideSuccess(data: Joke) {
                callback.provideText("success")
            }

            override fun provideError(error:JokeFailure) {
                callback.provideText("error")
            }
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