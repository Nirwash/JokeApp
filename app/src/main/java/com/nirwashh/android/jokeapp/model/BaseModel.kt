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
    private var jokeCallback: JokeCallback? = null
    private var cachedJokeServerModel: JokeServerModel? = null
    override fun getJoke() {
        cloudDataSource.getJoke(object : JokeCloudCallback {
            override fun provide(joke: JokeServerModel) {
                cachedJokeServerModel = joke
                jokeCallback?.provide(joke.toBaseJoke())
            }

            override fun fail(error: ErrorType) {
                cachedJokeServerModel = null
                val failure = if (error == ErrorType.NO_CONNECTION) noConnection
                else serviceUnavailable
                jokeCallback?.provide(FailedJoke(failure.getMessage()))
            }
        })
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