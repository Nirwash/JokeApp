package com.nirwashh.android.jokeapp.utils

import android.app.Application
import com.google.gson.Gson
import com.nirwashh.android.jokeapp.api.JokeService
import com.nirwashh.android.jokeapp.domain.BaseResourceManager
import com.nirwashh.android.jokeapp.model.BaseModel
import com.nirwashh.android.jokeapp.viewmodel.ViewModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class JokeApp: Application() {

    lateinit var viewModel: ViewModel

    override fun onCreate() {
        super.onCreate()
        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.google.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
//        viewModel = ViewModel(
//            BaseModel(
//                retrofit.create(JokeService::class.java),
//                BaseResourceManager(this)
//            )
//        )
        viewModel = ViewModel(BaseModel(TestCacheDataSource(),
            TestCloudDataSource(),
            BaseResourceManager(this)))
    }
}