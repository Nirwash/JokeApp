package com.nirwashh.android.jokeapp.model

import com.google.gson.annotations.SerializedName
import com.nirwashh.android.jokeapp.domain.Joke

data class JokeDTO(
    @SerializedName("fact")
    private val text: String
) {
    fun toJoke() = Joke(text)
}
