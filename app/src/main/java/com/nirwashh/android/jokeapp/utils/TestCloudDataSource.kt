package com.nirwashh.android.jokeapp.utils

import com.nirwashh.android.jokeapp.api.JokeCloudCallback
import com.nirwashh.android.jokeapp.domain.CloudDataSource
import com.nirwashh.android.jokeapp.model.JokeServerModel

class TestCloudDataSource : CloudDataSource {
    private var count = 0
    override fun getJoke(callback: JokeCloudCallback) {
        val joke = JokeServerModel("test $count", count)
        callback.provide(joke)
        count++
    }
}