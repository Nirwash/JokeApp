package com.nirwashh.android.jokeapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.nirwashh.android.jokeapp.viewmodel.*
import com.nirwashh.android.jokeapp.databinding.ActivityMainBinding
import com.nirwashh.android.jokeapp.viewmodel.DataCallback

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
            checkbox.setOnCheckedChangeListener { _, isChecked ->
                viewModel.chooseFavorites(isChecked)
            }
            changeButton.setOnClickListener {
                viewModel.changeJokeStatus()
            }
        }


        viewModel.init(object : DataCallback {
            override fun provideText(text: String) = runOnUiThread {
                b.apply {
                    btnGetJoke.isEnabled = true
                    progressbar.visibility = View.INVISIBLE
                    tvJoke.text = text
                }

            }

            override fun provideIconRes(id: Int) = runOnUiThread {
                b.changeButton.setImageResource(id)
            }
        })
    }

    override fun onDestroy() {
        viewModel.clear()
        super.onDestroy()
    }
}

