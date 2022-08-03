package com.nirwashh.android.jokeapp.model

import com.nirwashh.android.jokeapp.domain.Joke
import com.nirwashh.android.jokeapp.domain.JokeFailure

interface Model {

    fun getJoke()

    fun init(callback: ResultCallback)

    fun clear()
}

interface ResultCallback {
    fun provideSuccess(data: Joke)

    fun provideError(error: JokeFailure)
}