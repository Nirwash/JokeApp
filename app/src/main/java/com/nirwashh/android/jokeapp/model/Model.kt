package com.nirwashh.android.jokeapp.model

import com.nirwashh.android.jokeapp.domain.Joke

interface Model {

    fun getJoke()

    fun init(jokeCallback: JokeCallback)

    fun changeJokeStatus(jokeCallback: JokeCallback)

    fun chooseDataSource(cached: Boolean)

    fun clear()
}

interface JokeCallback {
    fun provide(joke: Joke)
}

interface JokeCachedCallback {
    fun provide(jokeServerModel: JokeServerModel)

    fun fail()
}