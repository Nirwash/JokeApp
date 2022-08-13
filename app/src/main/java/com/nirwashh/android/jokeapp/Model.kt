package com.nirwashh.android.jokeapp

interface Model {

    fun getJoke()

    fun init(jokeCallback: JokeCallback)

    fun changeJokeStatus(jokeCallback: JokeCallback)

    fun chooseDataSource(cached: Boolean)

    fun clear()
}

interface JokeCallback {
    fun provide(jokeUIModel: JokeUIModel)
}





