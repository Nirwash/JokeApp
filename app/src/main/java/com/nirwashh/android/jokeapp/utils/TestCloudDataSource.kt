package com.nirwashh.android.jokeapp.utils

import com.nirwashh.android.jokeapp.api.JokeCloudCallback
import com.nirwashh.android.jokeapp.domain.CloudDataSource
import com.nirwashh.android.jokeapp.model.JokeServerModel

class TestCloudDataSource : CloudDataSource {
    override fun getJoke(callback: JokeCloudCallback) {
        callback.provide(JokeServerModel("test Text", 0))
    }
}