package com.nirwashh.android.jokeapp.api

import com.nirwashh.android.jokeapp.model.JokeServerModel
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
    fun provide(joke: JokeServerModel)
    fun fail(error: ErrorType)
}

enum class ErrorType {
    NO_CONNECTION,
    SERVICE_UNAVAILABLE
}