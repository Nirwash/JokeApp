package com.nirwashh.android.jokeapp.api

import com.nirwashh.android.jokeapp.model.JokeDTO
import retrofit2.Call
import retrofit2.http.GET

interface JokeService {


    @GET("https://type.fit/api/quotes")
    fun getJoke() : Call<JokeDTO>

}

interface ServiceCallBack {

    fun returnSuccess(data: JokeDTO)

    fun returnError(type: ErrorType)

}

enum class ErrorType {
    NO_CONNECTION,
    OTHER
}