package com.nirwashh.android.jokeapp.utils

import com.nirwashh.android.jokeapp.domain.CacheDataSource
import com.nirwashh.android.jokeapp.domain.Joke
import com.nirwashh.android.jokeapp.model.JokeServerModel

class TestCacheDataSource : CacheDataSource {

    private val map = HashMap<Int, JokeServerModel>()

    override fun addOrRemove(id: Int, jokeServerModel: JokeServerModel): Joke {
        return if (map.containsKey(id)) {
            val joke = map[id]!!.toBaseJoke()
            map.remove(id)
            joke
        } else {
            map[id] = jokeServerModel
            jokeServerModel.toFavoriteJoke()

        }
    }
}