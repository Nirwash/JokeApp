package com.nirwashh.android.jokeapp


class TestCacheDataSource : CacheDataSource {

    private val list = ArrayList<Pair<Int, Joke>>()
    override fun getJoke(jokeCachedCallback: JokeCachedCallback) {
        if (list.isEmpty()) {
            jokeCachedCallback.fail()
        } else {
            jokeCachedCallback.provide(list.random().second)
        }
    }

    override fun addOrRemove(id: Int, joke: Joke): JokeUIModel {
        val found = list.find { it.first == id }
        return if (found != null) {
            val joke = found.second.toBaseJoke()
            list.remove(found)
            joke
        } else {
            list.add(Pair(id, joke))
            joke.toFavoriteJoke()
        }
    }

}