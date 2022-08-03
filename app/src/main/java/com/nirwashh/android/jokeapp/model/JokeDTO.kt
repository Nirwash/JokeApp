package com.nirwashh.android.jokeapp.model

import com.google.gson.annotations.SerializedName
import com.nirwashh.android.jokeapp.domain.Joke

data class JokeDTO(
    @SerializedName("text")
    private val text: String,
    @SerializedName("author")
    private val punchline: String
) {
    fun toJoke() = Joke(text, punchline)
}
