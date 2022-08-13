package com.nirwashh.android.jokeapp

import com.google.gson.annotations.SerializedName

class Joke(
    private val text: String,
    private val id: Int
) {
    fun toBaseJoke() = BaseJokeUIModel(text)
    fun toFavoriteJoke() = FavoriteJokeUIModel(text)
    fun toJokeRealm(): JokeRealm {
        return JokeRealm().also {
            it.id = id
            it.text = text
        }
    }
    fun change(cacheDataSource: CacheDataSource) = cacheDataSource.addOrRemove(id, this)
}