package com.nirwashh.android.jokeapp.utils

import android.app.Application
import com.nirwashh.android.jokeapp.viewmodel.ViewModel

class JokeApp: Application() {

    lateinit var viewModel: ViewModel

    override fun onCreate() {
        super.onCreate()
        viewModel = ViewModel(TestModel())
    }
}