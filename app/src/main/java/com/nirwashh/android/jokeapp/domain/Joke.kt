package com.nirwashh.android.jokeapp.domain

class Joke(private val text: String, private val punchline: String) {

    fun getJokeUi() = "$text\n$punchline"
}