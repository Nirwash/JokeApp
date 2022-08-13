package com.nirwashh.android.jokeapp

class TestCloudDataSource : CloudDataSource {
    private var count = 0
    override fun getJoke(callback: JokeCloudCallback) {
        val joke = Joke("test $count", count)
        callback.provide(joke)
        count++
    }
}