package com.nirwashh.android.jokeapp.model

import com.nirwashh.android.jokeapp.api.ErrorType
import com.nirwashh.android.jokeapp.api.JokeCloudCallback
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
    private var cachedJokeServerModel: JokeServerModel? = null
    private var getJokeFromCache = false

    override fun chooseDataSource(cached: Boolean) {
        getJokeFromCache = cached
    }

    override fun getJoke() {
        if (getJokeFromCache) {
            cacheDataSource.getJoke(object : JokeCachedCallback {
                override fun provide(jokeServerModel: JokeServerModel) {
                    cachedJokeServerModel = jokeServerModel
                    jokeCallback?.provide(jokeServerModel.toFavoriteJoke())
                }

                override fun fail() {
                    cachedJokeServerModel = null
                    jokeCallback?.provide(FailedJoke(noCachedJokes.getMessage()))
                }
            })
        } else {
            cloudDataSource.getJoke(object : JokeCloudCallback {
                override fun provide(joke: JokeServerModel) {
                    cachedJokeServerModel = joke
                    jokeCallback?.provide(joke.toBaseJoke())
                }

                override fun fail(error: ErrorType) {
                    cachedJokeServerModel = null
                    val failure = if (error == ErrorType.NO_CONNECTION) noConnection else serviceUnavailable
                    jokeCallback?.provide(FailedJoke(failure.getMessage()))
                }
            })
        }
    }


    override fun init(jokeCallback: JokeCallback) {
        this.jokeCallback = jokeCallback
    }

    override fun changeJokeStatus(jokeCallback: JokeCallback) {
        cachedJokeServerModel?.change(cacheDataSource)?.let {
            jokeCallback.provide(it)
        }
    }



    override fun clear() {
        jokeCallback = null
    }
}