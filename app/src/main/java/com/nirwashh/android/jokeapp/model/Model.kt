package com.nirwashh.android.jokeapp.model

import com.nirwashh.android.jokeapp.R
import com.nirwashh.android.jokeapp.domain.BaseResourceManager
import com.nirwashh.android.jokeapp.domain.Joke
import com.nirwashh.android.jokeapp.domain.JokeFailure
import com.nirwashh.android.jokeapp.domain.ResourceManager

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



