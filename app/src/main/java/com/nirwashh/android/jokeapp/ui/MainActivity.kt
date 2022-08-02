package com.nirwashh.android.jokeapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.nirwashh.android.jokeapp.viewmodel.*
import com.nirwashh.android.jokeapp.databinding.ActivityMainBinding
import com.nirwashh.android.jokeapp.utils.JokeApp
import com.nirwashh.android.jokeapp.viewmodel.TextCallback

class MainActivity : AppCompatActivity() {
    private lateinit var b: ActivityMainBinding
    private lateinit var viewModel: ViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityMainBinding.inflate(layoutInflater)
        setContentView(b.root)
        viewModel = (application as JokeApp).viewModel

        b.apply {
            progressbar.visibility = View.INVISIBLE
            btnGetJoke.setOnClickListener {
                btnGetJoke.isEnabled = false
                progressbar.visibility = View.VISIBLE
                viewModel.getJoke()
            }
        }


        viewModel.init(object : TextCallback {
            override fun provideText(text: String) = runOnUiThread {
                b.apply {
                    btnGetJoke.isEnabled = true
                    progressbar.visibility = View.INVISIBLE
                    tvJoke.text = text
                }

            }
        })
    }

    override fun onDestroy() {
        viewModel.clear()
        super.onDestroy()
    }
}

