package com.nirwashh.android.jokeapp.model

import com.google.gson.annotations.SerializedName
import com.nirwashh.android.jokeapp.domain.BaseJoke
import com.nirwashh.android.jokeapp.domain.CacheDataSource
import com.nirwashh.android.jokeapp.domain.FavoriteJoke
import java.lang.Math.random

data class JokeServerModel(
    @SerializedName("fact")
    private val text: String,
    @SerializedName("length")
    private val id: Int
) {
    fun toBaseJoke() = BaseJoke(text)

    fun toFavoriteJoke() = FavoriteJoke(text)

    fun change(cacheDataSource: CacheDataSource) = cacheDataSource.addOrRemove(id, this)
}
