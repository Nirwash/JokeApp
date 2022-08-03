package com.nirwashh.android.jokeapp.utils

import com.nirwashh.android.jokeapp.domain.Joke
import com.nirwashh.android.jokeapp.domain.NoConnection
import com.nirwashh.android.jokeapp.domain.ResourceManager
import com.nirwashh.android.jokeapp.domain.ServiceUnavailable
import com.nirwashh.android.jokeapp.model.Model
import com.nirwashh.android.jokeapp.model.ResultCallback

class TestModel(resourceManager: ResourceManager) : Model {

    private var callback: ResultCallback? = null
    private var count = 0
    private val noConnection = NoConnection(resourceManager)
    private val serviceUnavailable = ServiceUnavailable(resourceManager)


    override fun getJoke() {
        Thread {
            Thread.sleep(1000)
            when (count) {
                0 -> callback?.provideSuccess(Joke("testText", "testPunchline"))
                1 -> callback?.provideError(noConnection)
                2 -> callback?.provideError(serviceUnavailable)
            }
            count++
            if (count == 3) count = 0
        }.start()

    }

    override fun init(callback: ResultCallback) {
        this.callback = callback
    }

    override fun clear() {
        callback = null
    }
}