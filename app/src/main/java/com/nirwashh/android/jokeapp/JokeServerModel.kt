package com.nirwashh.android.jokeapp

import com.google.gson.annotations.SerializedName

data class JokeServerModel(
    @SerializedName("fact")
    private val text: String,
    @SerializedName("length")
    private val id: Int
) {
    fun toJoke() = Joke(id = id, text = text)
}




