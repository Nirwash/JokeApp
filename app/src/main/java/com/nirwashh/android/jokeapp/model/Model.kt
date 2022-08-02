package com.nirwashh.android.jokeapp.model

interface Model<S, E> {

    fun getJoke()

    fun init(callback: ResultCallback<S, E>)

    fun clear()
}

interface ResultCallback<S, E> {
    fun provideSuccess(data: S)

    fun provideError(error: E)
}