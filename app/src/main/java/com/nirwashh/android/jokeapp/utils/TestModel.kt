package com.nirwashh.android.jokeapp.utils

import com.nirwashh.android.jokeapp.domain.*
import com.nirwashh.android.jokeapp.model.Model
import com.nirwashh.android.jokeapp.model.JokeCallback

class TestModel(resourceManager: ResourceManager) : Model {

    private var callback: JokeCallback? = null
    private var count = 0
    private val noConnection = NoConnection(resourceManager)
    private val serviceUnavailable = ServiceUnavailable(resourceManager)


    override fun getJoke() {
        Thread {
            Thread.sleep(1000)
            when (count) {
                0 -> callback?.provide(BaseJoke("test text"))
                1 -> callback?.provide(FavoriteJoke("favorite text"))
                2 -> callback?.provide(FailedJoke(serviceUnavailable.getMessage()))
            }
            count++
            if (count == 3) count = 0
        }.start()

    }

    override fun init(jokeCallback: JokeCallback) {
        this.callback = jokeCallback
    }

    override fun changeJokeStatus(jokeCallback: JokeCallback) {
        TODO()
    }

    override fun clear() {
        callback = null
    }
}