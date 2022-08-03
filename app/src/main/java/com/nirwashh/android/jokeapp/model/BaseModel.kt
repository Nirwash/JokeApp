package com.nirwashh.android.jokeapp.model

import com.nirwashh.android.jokeapp.api.ErrorType
import com.nirwashh.android.jokeapp.api.JokeService
import com.nirwashh.android.jokeapp.api.ServiceCallBack
import com.nirwashh.android.jokeapp.domain.Joke
import com.nirwashh.android.jokeapp.domain.NoConnection
import com.nirwashh.android.jokeapp.domain.ResourceManager
import com.nirwashh.android.jokeapp.domain.ServiceUnavailable
import retrofit2.Call
import retrofit2.Response
import java.net.UnknownHostException

class BaseModel(
    private val service: JokeService,
    private val resourceManager: ResourceManager
) : Model {
    private var callback: ResultCallback? = null
    private val noConnection by lazy { NoConnection(resourceManager) }
    private val serviceUnavailable by lazy { ServiceUnavailable(resourceManager) }

    override fun getJoke() {
        service.getJoke().enqueue(object : retrofit2.Callback<JokeDTO> {
            override fun onResponse(call: Call<JokeDTO>, response: Response<JokeDTO>) {
                if (response.isSuccessful) {
                    callback?.provideSuccess(response.body()!!.toJoke())
                } else {
                    callback?.provideError(serviceUnavailable)
                }
            }

            override fun onFailure(call: Call<JokeDTO>, t: Throwable) {
                if (t is UnknownHostException) {
                    callback?.provideError(noConnection)
                } else {
                    callback?.provideError(serviceUnavailable)
                }
            }
        })
    }

    override fun init(callback: ResultCallback) {
        this.callback = callback
    }

    override fun clear() {
        callback = null
    }
}