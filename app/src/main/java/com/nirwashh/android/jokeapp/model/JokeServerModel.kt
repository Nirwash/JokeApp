package com.nirwashh.android.jokeapp.model

import com.google.gson.annotations.SerializedName
import com.nirwashh.android.jokeapp.domain.BaseJoke
import com.nirwashh.android.jokeapp.domain.CacheDataSource
import java.lang.Math.random

data class JokeServerModel(
    @SerializedName("fact")
    private val text: String,
    @SerializedName("length")
    private val _id: Int
) {
    private val id = (_id * random()).toInt()

    fun toBaseJoke() = BaseJoke(text)

    fun change(cacheDataSource: CacheDataSource) = cacheDataSource.addOrRemove(id, this)
}
