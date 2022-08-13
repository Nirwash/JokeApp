package com.nirwashh.android.jokeapp

import com.nirwashh.android.jokeapp.domain.*

class BaseModel(
    private val cacheDataSource: CacheDataSource,
    private val cloudDataSource: CloudDataSource,
    private val resourceManager: ResourceManager
) : Model {
    private val noConnection by lazy { NoConnection(resourceManager) }
    private val serviceUnavailable by lazy { ServiceUnavailable(resourceManager) }
    private val noCachedJokes by lazy { NoCachedJokes(resourceManager) }
    private var jokeCallback: JokeCallback? = null
    private var cachedJoke: Joke? = null
    private var getJokeFromCache = false

    override fun chooseDataSource(cached: Boolean) {
        getJokeFromCache = cached
    }

    override fun getJoke() {
        if (getJokeFromCache) {
            cacheDataSource.getJoke(object : JokeCachedCallback {
                override fun provide(joke: Joke) {
                    cachedJoke = joke
                    jokeCallback?.provide(joke.toFavoriteJoke())
                }

                override fun fail() {
                    cachedJoke = null
                    jokeCallback?.provide(FailedJokeUIModel(noCachedJokes.getMessage()))
                }
            })
        } else {
            cloudDataSource.getJoke(object : JokeCloudCallback {
                override fun provide(joke: Joke) {
                    cachedJoke = joke
                    jokeCallback?.provide(joke.toBaseJoke())
                }

                override fun fail(error: ErrorType) {
                    cachedJoke = null
                    val failure = if (error == ErrorType.NO_CONNECTION) noConnection else serviceUnavailable
                    jokeCallback?.provide(FailedJokeUIModel(failure.getMessage()))
                }
            })
        }
    }


    override fun init(jokeCallback: JokeCallback) {
        this.jokeCallback = jokeCallback
    }

    override fun changeJokeStatus(jokeCallback: JokeCallback) {
        cachedJoke?.let {
            jokeCallback.provide(it.change(cacheDataSource))
        }
    }



    override fun clear() {
        jokeCallback = null
    }
}