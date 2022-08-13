package com.nirwashh.android.jokeapp

import retrofit2.Call
import retrofit2.http.GET

interface JokeService {


    @GET("https://catfact.ninja/fact")
    fun getJoke() : Call<JokeServerModel>

}

interface ServiceCallBack {

    fun returnSuccess(data: JokeServerModel)

    fun returnError(type: ErrorType)

}

interface JokeCloudCallback {
    fun provide(joke: Joke)
    fun fail(error: ErrorType)
}

enum class ErrorType {
    NO_CONNECTION,
    SERVICE_UNAVAILABLE
}