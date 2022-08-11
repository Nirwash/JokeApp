package com.nirwashh.android.jokeapp.utils

import com.nirwashh.android.jokeapp.domain.CacheDataSource
import com.nirwashh.android.jokeapp.domain.Joke
import com.nirwashh.android.jokeapp.model.JokeCachedCallback
import com.nirwashh.android.jokeapp.model.JokeServerModel

class TestCacheDataSource : CacheDataSource {

    private val list = ArrayList<Pair<Int, JokeServerModel>>()
    override fun getJoke(jokeCachedCallback: JokeCachedCallback) {
        if (list.isEmpty()) {
            jokeCachedCallback.fail()
        } else {
            jokeCachedCallback.provide(list.random().second)
        }
    }

    override fun addOrRemove(id: Int, jokeServerModel: JokeServerModel): Joke {
        val found = list.find { it.first == id }
        return if (found != null) {
            val joke = found.second.toBaseJoke()
            list.remove(found)
            joke
        } else {
            list.add(Pair(id, jokeServerModel))
            jokeServerModel.toFavoriteJoke()
        }
    }

}